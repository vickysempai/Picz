package Filters;

import android.graphics.Bitmap;


public class BlackWhiteFilter implements iFilterable {
    double intensidad=1;

    public Bitmap makeFilter(Bitmap photo){
        photo = photo.copy( Bitmap.Config.ARGB_8888 , true);
        int vertical = photo.getHeight();
        int horizontal = photo.getWidth();
        int rojo,pixel,verde,azul,suma,gris;

        for (int contadorY = 0; contadorY < vertical; contadorY++)
            for (int contadorX = 0; contadorX < horizontal; contadorX++) {

            //mi metodo

                pixel = photo.getPixel(contadorX, contadorY);

                azul = pixel & 0x000000FF;
                verde = pixel & 0x0000FF00 >> 8;
                rojo = pixel & 0x00FF0000 >> 16;
                suma = (azul+verde+rojo)/3;
                rojo = suma + (int)((rojo - suma) * intensidad);
                verde = suma + (int)((verde - suma) * intensidad);
                azul = suma + (int)((azul - suma) * intensidad);
                gris = ((pixel & 0xFF000000) | (rojo << 16) | (verde << 8)) | azul;
                //Log.w("mitag","1");
                photo.setPixel(contadorX, contadorY, gris);
                //Log.w("mitag","1");

                //metodo original
               /* Color color = Color.valueOf(photo.getPixel(contadorX, contadorY));
                float red = color.red();
                float blue = color.blue();
                float green = color.green();
                int gray = (int) ((red + blue + green) / 3);
                photo.setPixel(contadorX, contadorY, gray);*/

            }
            return photo;

    }

    @Override
    public double[][] createKernel(double sigma) {
        intensidad = sigma*5;
        return null;
    }
}
