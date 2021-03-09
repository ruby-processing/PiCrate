/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monkstone.noise;

/**
 *
 * @author Martin Prout
 */
public class NoiseGenerator implements Noise {

    private Noise implementation;
    private NoiseMode mode;
    private Long seed;

    public NoiseGenerator() {
        this.implementation = new OpenSimplex2F(System.currentTimeMillis());
        this.mode = NoiseMode.DEFAULT;
    }

    @Override
    public void noiseMode(NoiseMode mode) {
        if (this.mode != mode && this.mode != NoiseMode.DEFAULT) {
            seed = System.currentTimeMillis();
            this.implementation = new OpenSimplex2F(seed);
            this.mode = NoiseMode.DEFAULT;
        }
        if (this.mode != mode && this.mode != NoiseMode.OPEN_DETAIL) {
            this.implementation = new OpenSimplex2S(seed);
            this.mode = NoiseMode.OPEN_DETAIL;
        }
    }

    public NoiseMode noiseMode(){
        return this.mode;
    }

    @Override
    public float noise(float x, float y, float z) {
        return implementation.noise(x, y, z);
    }

    @Override
    public float noise(float x, float y, float z, float w) {
        return implementation.noise(x, y, z, w);
    }

    @Override
    public void noiseSeed(long seed) {
        implementation.noiseSeed(seed);
    }

}
