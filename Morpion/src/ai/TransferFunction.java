package ai;

public interface TransferFunction{
	public double evalute(double value, double[] values);
	public double evaluteDerivate(double value, double[] values);
}