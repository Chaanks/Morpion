package ai;

import java.io.Serializable;

public class SoftMaxTransferFunction implements TransferFunction, Serializable {

	double[] values;
	
	public void setValues(double[] values) {this.values = values;}
	
	@Override
	public double evalute(double value, double[] values) {
		
		double expSum = 0;
		for(Double d : values)
		    expSum += Math.exp(d);

		return Math.exp(value) / expSum;
	}

	@Override
	public double evaluteDerivate(double value, double[] values) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
