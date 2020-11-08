package code;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * RSA encryption
 * @author credit to: https://stackoverflow.com/questions/48939063/java-send-encrypted-text-from-client-through-socket-using-rsa-and-decrypt-it-in
 */
public class RSA {
    private BigInteger n;
    private BigInteger d;
    private BigInteger e;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public static class PublicKey {
        public byte[] getE() {
            return e;
        }

        public byte[] getN() {
            return n;
        }

        private final byte[] e;
        private final byte[] n;

        public PublicKey(byte[] e, byte[] n) {
            this.e = e;
            this.n = n;
        }
    }

    public static class PrivateKey {
        public byte[] getD() {
            return d;
        }
        public byte[] getN() {
            return n;
        }
        private final byte[] d;
        private final byte[] n;

        public PrivateKey(byte[] n, byte[] d) {
            this.n = n;
            this.d = d;
        }
    }

    public RSA() {
        generateKeyPair(1024);
    }

    public RSA(int bits) {
        generateKeyPair(bits);
    }

    public final void generateKeyPair(int bits) {

        SecureRandom random = new SecureRandom();

        BigInteger p = new BigInteger(bits/2, 100, random);
        BigInteger q = new BigInteger(bits/2, 100, random);
        BigInteger phi = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));

        n = p.multiply(q);
        e = new BigInteger("3");


        while (phi.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }

        d = e.modInverse(phi);
        publicKey = new PublicKey(e.toByteArray(), n.toByteArray());
        privateKey = new PrivateKey(d.toByteArray(), n.toByteArray());
    }


    public byte[] encrypt(byte[] plainData, PublicKey publicKey) {
        return ((new BigInteger(plainData)).modPow(new BigInteger(publicKey.getE()), new BigInteger(publicKey.getN()))).toByteArray();
    }

    public byte[] decrypt(byte[] encryptedData) {
        return ((new BigInteger(encryptedData)).modPow(d, n)).toByteArray();
    }

    // (d, n)
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    // (e, n)
    public PublicKey getPublicKey() {
        return publicKey;
    }
}