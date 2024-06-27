package com.hezihan.calculator;

public class ConvertingUnits
{
    //面积单位转换，平方毫米、平方米、平方厘米、平方千米、英亩、公顷
    static class Area{
        public double sqMilliToMeter(double milli)
        {
            return (milli/1000000);
        }

        public double sqMeterToMilli(double meter)
        {
            return (meter*1000000);
        }

        public double sqCentiToMeter(double Centi)
        {
            return (Centi/10000);
        }

        public double sqMeterToCenti(double meter)
        {
            return (meter*10000);
        }

        public double sqKiloToMeter(double Kilo)
        {
            return (Kilo*1000000);
        }

        public double sqMeterToKilo(double meter)
        {
            return (meter/1000000);
        }

        public double AcreToMeter(double acre)
        {
            return (acre*4046.86);
        }

        public double sqMeterToAcre(double meter)
        {
            return (meter/4046.86);
        }

        public double HectareToMeter(double Hectare)
        {
            return (Hectare*10000);
        }

        public double sqMeterToHectare(double meter)
        {
            return (meter/10000);
        }
    }

    //长度单位转换，毫米、米、厘米、千米、英寸、英尺、码、英里
    static class Length{
        public double MilliToMeter(double milli)
        {
            return (milli/1000);
        }

        public double MeterToMilli(double meter)
        {
            return (meter*1000);
        }

        public double CentiToMeter(double Centi)
        {
            return (Centi/100);
        }

        public double MeterToCenti(double meter)
        {
            return (meter*100);
        }

        public double KiloToMeter(double Kilo)
        {
            return (Kilo*1000);
        }

        public double MeterToKilo(double meter)
        {
            return (meter/1000);
        }

        public double InchToMeter(double Inch)
        {
            return (Inch/39.3701);
        }

        public double MeterToInch(double meter)
        {
            return (meter*39.3701);
        }

        public double FootToMeter(double Foot)
        {
            return (Foot/3.28084);
        }

        public double MeterToFoot(double meter)
        {
            return (meter*3.28084);
        }

        public double YardToMeter(double Yard)
        {
            return (Yard/1.09361);
        }

        public double MeterToYard(double meter)
        {
            return (meter*1.09361);
        }

        public double MileToMeter(double Mile)
        {
            return (Mile/0.000621371);
        }

        public double MeterToMile(double meter)
        {
            return (meter*0.000621371);
        }

        public double NanoToMeter(double milli)
        {
            return (milli/1000000000);
        }

        public double MeterToNano(double meter)
        {
            return (meter*1000000000);
        }

    }

    //温度单位转换，华氏度、开尔文、摄氏度
    static class Temperature{
        public double FerToKelvin(double fer)
        {
            return ((fer+459.67)*5/9);
        }

        public double KelvinToFer(double kelvin)
        {
            return ((kelvin*9/5)-459.67);
        }

        public double CelsiTokelvin(double Celsi)
        {
            return (Celsi+273.15);
        }

        public double KelvinToCelsi(double Kelvin)
        {
            return (Kelvin-273.15);
        }
    }

    //质量单位转换，毫克、千克、克、厘克、公吨、磅、盎司
    static class Weight
    {
        public double MilliToKilo(double milli)
        {
            return (milli/1000000);
        }

        public double KiloToMilli(double Kilo)
        {
            return (Kilo*1000000);
        }

        public double GramToKilo(double Gram)
        {
            return (Gram/1000);
        }

        public double KiloToGram(double Kilo)
        {
            return (Kilo*1000);
        }

        public double CentiToKilo(double Centi)
        {
            return (Centi/100000);
        }

        public double KiloToCenti(double Kilo)
        {
            return (Kilo*100000);
        }

        public double DeciToKilo(double Deci)
        {
            return (Deci/10000);
        }

        public double KiloToDeci(double Kilo)
        {
            return (Kilo*10000);
        }

        public double MetricTonnesToKilo(double MetricTonnes)
        {
            return (MetricTonnes*1000);
        }

        public double KiloToMetricTonnes(double Kilo)
        {
            return (Kilo/1000);
        }

        public double PoundsToKilo(double Pounds)
        {
            return (Pounds/2.20462);
        }

        public double KiloToPounds(double Kilo)
        {
            return (Kilo*2.20462);
        }

        public double OuncesToKilo(double Ounces)
        {
            return (Ounces/35.274);
        }

        public double KiloToOunces(double Kilo)
        {
            return (Kilo*35.274);
        }
    }
}