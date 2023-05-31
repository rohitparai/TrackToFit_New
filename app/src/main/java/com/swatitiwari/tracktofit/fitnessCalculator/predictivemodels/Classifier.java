package com.swatitiwari.tracktofit.fitnessCalculator.predictivemodels;

public interface Classifier {
	String name();

	Classification recognize(final float[] pixels);
}
