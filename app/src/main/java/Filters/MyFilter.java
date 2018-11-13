package Filters;

import android.graphics.Bitmap;

public class MyFilter implements iFilterable {


    public Bitmap makeFilter(Bitmap photo) {
        photo = photo.copy( Bitmap.Config.ARGB_8888 , true);

        int vertical = photo.getHeight();
        int horizontal = photo.getWidth();


        for (int contadorY = 1; contadorY < vertical-1; contadorY++){ //Se usa 1 para no utilizar los bordes
            for (int contadorX = 1; contadorX < horizontal-1; contadorX++) {


                int pixel = photo.getPixel(contadorX,contadorY);
                int azul = pixel & 0x000000FF ;
                int verde = pixel & 0x0000FF00 >> 8;
                int rojo = pixel & 0x00FF0000 >> 32;
                int alfa = pixel & 0xFF000000 >> 64;

                int cambiada = (alfa << 64 | (rojo << 32) | (verde << 8)) | azul;

                photo.setPixel(contadorX,contadorY,cambiada);
            }

        }
        return photo;
    }

    @Override
    public double[][] createKernel(double sigma) {
        return null;
    }

}
