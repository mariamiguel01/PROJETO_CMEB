package org.feup.biosignals.projectbiosignals.helpers;

import java.util.ArrayList;
import java.util.List;


public class DataParser {
    public List<Float> getAccDataX() {
        return accDataX;
    }

    public List<Float> getAccDataY() {
        return accDataY;
    }

    public List<Float> getAccDataZ() {
        return accDataZ;
    }

    public List<Float> getGyroDataX() {
        return gyroDataX;
    }

    public List<Float> getGyroDataY() {
        return gyroDataY;
    }

    public List<Float> getGyroDataZ() {
        return gyroDataZ;
    }

    public List<Float> getMagDataX() {
        return magDataX;
    }

    public List<Float> getMagDataY() {
        return magDataY;
    }

    public List<Float> getMagDataZ() {
        return magDataZ;
    }

    public List<Float> getForceOneData() {
        return forceOneData;
    }

    public List<Float> getForceTwoData() {
        return forceTwoData;
    }

    private ArrayList<Float> accDataX;
    private ArrayList<Float> accDataY;
    private ArrayList<Float> accDataZ;

    private ArrayList<Float> gyroDataX;
    private ArrayList<Float> gyroDataY;
    private ArrayList<Float> gyroDataZ;

    private ArrayList<Float> magDataX;
    private ArrayList<Float> magDataY;
    private ArrayList<Float> magDataZ;

    private ArrayList<Float> forceOneData;
    private ArrayList<Float> forceTwoData;


    public DataParser() {
        this.accDataX = new ArrayList<Float>();
        this.accDataY = new ArrayList<Float>();
        this.accDataZ = new ArrayList<Float>();
        this.gyroDataX = new ArrayList<Float>();
        this.gyroDataY = new ArrayList<Float>();
        this.gyroDataZ = new ArrayList<Float>();
        this.magDataX = new ArrayList<Float>();
        this.magDataY = new ArrayList<Float>();
        this.magDataZ = new ArrayList<Float>();
        this.forceOneData = new ArrayList<Float>();
        this.forceTwoData = new ArrayList<Float>();
    }

    public void parse(byte[] raw){
        for(int i=0; i<raw.length-2;i+=4){
            int mod = i%44;

            if(mod==0){
                accDataX.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));
            }

            else if (mod==4){
                accDataY.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));
            }

            else if (mod==8){
                accDataZ.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));
            }

            else if (mod==12){
                gyroDataX.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));
            }

            else if (mod==16){
                gyroDataY.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));
            }

            else  if (mod==20) {
                gyroDataZ.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));
            }

            else if (mod==24) {
                magDataX.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));

            }

            else if (mod==28) {
                magDataY.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));

            }

            else if (mod==32) {
                magDataZ.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));
            }

            else if (mod==36) {
                forceOneData.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));
            }

            else if (mod==40) {
                forceTwoData.add(Float.intBitsToFloat(
                        (raw[i] & 0xFF)
                                | ((raw[i+1] & 0xFF) << 8)
                                | ((raw[i+2] & 0xFF) << 16)
                                | ((raw[i+3] & 0xFF) << 24)));
            }
        }
    }

}
