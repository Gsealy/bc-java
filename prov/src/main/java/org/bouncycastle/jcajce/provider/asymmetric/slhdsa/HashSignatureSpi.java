package org.bouncycastle.jcajce.provider.asymmetric.slhdsa;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.slhdsa.HashSLHDSASigner;

public class HashSignatureSpi
    extends java.security.SignatureSpi
{
    private final HashSLHDSASigner signer;

    protected HashSignatureSpi(HashSLHDSASigner signer)
    {
        this.signer = signer;
    }

    protected void engineInitVerify(PublicKey publicKey)
        throws InvalidKeyException
    {
        if (publicKey instanceof BCSLHDSAPublicKey)
        {
            BCSLHDSAPublicKey key = (BCSLHDSAPublicKey)publicKey;

            CipherParameters param = key.getKeyParams();

            signer.init(false, param);
        }
        else
        {
            throw new InvalidKeyException("unknown public key passed to SLH-DSA");
        }
    }

    protected void engineInitSign(PrivateKey privateKey, SecureRandom random)
        throws InvalidKeyException
    {
        this.appRandom = random;
        engineInitSign(privateKey);
    }

    protected void engineInitSign(PrivateKey privateKey)
        throws InvalidKeyException
    {
        if (privateKey instanceof BCSLHDSAPrivateKey)
        {
            BCSLHDSAPrivateKey key = (BCSLHDSAPrivateKey)privateKey;

            CipherParameters param = key.getKeyParams();

            if (appRandom != null)
            {
                signer.init(true, new ParametersWithRandom(param, appRandom));
            }
            else
            {
                signer.init(true, param);
            }
        }
        else
        {
            throw new InvalidKeyException("unknown private key passed to SLH-DSA");
        }
    }

    protected void engineUpdate(byte b)
        throws SignatureException
    {
        signer.update(b);
    }

    protected void engineUpdate(byte[] b, int off, int len)
        throws SignatureException
    {
        signer.update(b, off, len);
    }

    protected byte[] engineSign()
        throws SignatureException
    {
        try
        {
            byte[] sig = signer.generateSignature();

            return sig;
        }
        catch (Exception e)
        {
            throw new SignatureException(e.toString());
        }
    }

    protected boolean engineVerify(byte[] sigBytes)
        throws SignatureException
    {
        return signer.verifySignature(sigBytes);
    }

    protected void engineSetParameter(AlgorithmParameterSpec params)
    {
        // TODO
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /**
     * @deprecated replaced with #engineSetParameter(java.security.spec.AlgorithmParameterSpec)
     */
    protected void engineSetParameter(String param, Object value)
    {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /**
     * @deprecated
     */
    protected Object engineGetParameter(String param)
    {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    static public class Direct
        extends HashSignatureSpi
    {
        public Direct()
        {
            super(new HashSLHDSASigner());
        }
    }
}
