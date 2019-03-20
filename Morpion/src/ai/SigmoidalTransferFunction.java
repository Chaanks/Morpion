package ai;

import java.io.Serializable;

public class SigmoidalTransferFunction implements TransferFunction, Serializable {
	public double evalute(double value, double[] values) {return 1 / (1 + Math.exp(- value));}
	public double evaluteDerivate(double value, double[] values) {return (value * (1.0 - value));}
}
