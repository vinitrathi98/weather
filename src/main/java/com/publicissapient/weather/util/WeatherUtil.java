
package com.publicissapient.weather.util;


/**
 * 
 * @author Vinit Rathi
 * @version 1.0
 * @since 2022-09-05
 *
 */

public class WeatherUtil {

    /**
     * Converts temp to celsius.
     *
     * @param temp the temp
     * @return the float
     */
    public static float convertTempToCelsius(float temp) {
            return temp - 273.15F;
    }

}
