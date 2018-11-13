package Filters;

import android.graphics.Bitmap;

public interface iFilterable {
    double[][] kernel = new double[3][3];

    Bitmap makeFilter(Bitmap photo);

    double[][] createKernel(double sigma);
}
