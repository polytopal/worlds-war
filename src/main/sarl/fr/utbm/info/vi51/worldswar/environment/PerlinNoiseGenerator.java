package fr.utbm.info.vi51.worldswar.environment;

import fr.utbm.info.vi51.worldswar.utils.Grid;

public class PerlinNoiseGenerator {

	/**
	 * Generate a {@link Grid} from 0 to width-1 and from 0 to height-1 This
	 * grid contains random float generated with Perlin noise.
	 * 
	 * @param width
	 *            the width of the grid
	 * @param height
	 *            the height of the grid
	 * @param octaveCount
	 *            the octave number, more there are octaves, more the values
	 *            will be brought together
	 * @param min
	 *            the minimum value of a grid cell
	 * @param max
	 *            the maximum value of a grid cell
	 * @return the generated {@link Grid}
	 */
	public static Grid<Float> generatePerlinNoiseHeightGrid(int width, int height, int octaveCount, float min,
			float max) {
		final float range = max - min;
		final float[][] noise = generatePerlinNoise(width, height, octaveCount);
		final Grid<Float> grid = new Grid<>(0, width - 1, 0, height - 1);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				final float value = noise[x][y] * range + min;
				grid.set(x, y, new Float(value));
			}
		}
		return grid;
	}

	/**
	 * @param width
	 *            the width of the map created
	 * @param height
	 *            the height of the map created
	 * @return an array of arrays of random float from 0 to 1
	 */
	private static float[][] generateWhiteNoise(int width, int height) {
		final float[][] noise = new float[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				noise[x][y] = (float) Math.random();
			}
		}
		return noise;
	}

	/**
	 * @param x0
	 * @param x1
	 * @param alpha
	 *            the weight of x1. Must be between 0 and 1
	 * @return the linear interpolation between x0 and x1. If alpha=0 return x0,
	 *         if alpha=1 return x1.
	 */
	private static float interpolate(float x0, float x1, float alpha) {
		return x0 * (1 - alpha) + alpha * x1;
	}

	/**
	 * From a base noise, realize an interpolation of each point with its
	 * neighbors.
	 * 
	 * @param baseNoise
	 * @param octave
	 * @return
	 */
	private static float[][] generateSmoothNoise(float[][] baseNoise, int octave) {
		final int width = baseNoise.length;
		final int height = baseNoise[0].length;
		final float[][] smoothNoise = new float[width][height];

		final int samplePeriod = 1 << octave; // calculates 2 ^ k
		final float sampleFrequency = 1.0f / samplePeriod;
		for (int i = 0; i < width; i++) {
			final int sample_i0 = (i / samplePeriod) * samplePeriod;
			// wrap around
			final int sample_i1 = (sample_i0 + samplePeriod) % width;
			final float horizontal_blend = (i - sample_i0) * sampleFrequency;

			for (int j = 0; j < height; j++) {
				final int sample_j0 = (j / samplePeriod) * samplePeriod;
				// wrap around
				final int sample_j1 = (sample_j0 + samplePeriod) % height;
				final float vertical_blend = (j - sample_j0) * sampleFrequency;
				final float top = interpolate(baseNoise[sample_i0][sample_j0], baseNoise[sample_i1][sample_j0],
						horizontal_blend);
				final float bottom = interpolate(baseNoise[sample_i0][sample_j1], baseNoise[sample_i1][sample_j1],
						horizontal_blend);
				// the cell height is the interpolation between top and bottom
				// interpolations
				smoothNoise[i][j] = interpolate(top, bottom, vertical_blend);
			}
		}

		return smoothNoise;
	}

	/**
	 * From a base noise, realize a Perlin noise by applying the function
	 * generateSmoothNoise for each octave
	 * 
	 * @param baseNoise
	 * @param octaveCount
	 * @return
	 */
	private static float[][] generatePerlinNoise(float[][] baseNoise, int octaveCount) {
		final int width = baseNoise.length;
		final int height = baseNoise[0].length;
		final float[][][] smoothNoise = new float[octaveCount][][];
		// an array of 2D arrays containing
		final float persistance = 0.7f;

		// call the function generateSmoothNoise for each octave and stock each
		// generated map in an array
		for (int i = 0; i < octaveCount; i++) {
			smoothNoise[i] = generateSmoothNoise(baseNoise, i);
		}

		final float[][] perlinNoise = new float[width][height];
		// an array of floats initialized to 0

		float amplitude = 1.0f;
		float totalAmplitude = 0.0f;

		// compute the Perlin noise by adding all the map previously computated
		for (int octave = octaveCount - 1; octave >= 0; octave--) {
			amplitude *= persistance;
			totalAmplitude += amplitude;

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					perlinNoise[i][j] += smoothNoise[octave][i][j] * amplitude;
				}
			}
		}

		// resize the height of each cell
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				perlinNoise[i][j] /= totalAmplitude;
			}
		}

		return perlinNoise;
	}

	private static float[][] generatePerlinNoise(int width, int height, int octaveCount) {
		final float[][] baseNoise = generateWhiteNoise(width, height);
		return generatePerlinNoise(baseNoise, octaveCount);
	}

}
