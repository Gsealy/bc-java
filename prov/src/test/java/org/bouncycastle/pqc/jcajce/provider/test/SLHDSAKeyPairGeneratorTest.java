package org.bouncycastle.pqc.jcajce.provider.test;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.spec.SLHDSAParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;


/**
 * KeyFactory/KeyPairGenerator tests for SLHDSA with the BC provider.
 */
public class SLHDSAKeyPairGeneratorTest
    extends KeyPairGeneratorTest
{

    protected void setUp()
    {
        super.setUp();
        Security.addProvider(new BouncyCastleProvider());
    }

    public void testKeyFactory()
        throws Exception
    {
        kf = KeyFactory.getInstance("SLH-DSA", "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_sha2_128s.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_sha2_128f.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_sha2_192s.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_sha2_192f.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_sha2_256s.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_sha2_256f.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_shake_128s.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_shake_128f.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_shake_192s.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_shake_192f.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_shake_256s.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_slh_dsa_shake_256f.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128s_with_sha256.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128f_with_sha256.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192s_with_sha512.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192f_with_sha512.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256s_with_sha512.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256f_with_sha512.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_shake_128s_with_shake128.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_shake_128f_with_shake128.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_shake_192s_with_shake256.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_shake_192f_with_shake256.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_shake_256s_with_shake256.getId(), "BC");
        kf = KeyFactory.getInstance(NISTObjectIdentifiers.id_hash_slh_dsa_shake_256f_with_shake256.getId(), "BC");
    }

    public void testKeySpecs()
        throws Exception
    {
        kf = KeyFactory.getInstance("SLH-DSA", "BC");
        kpg = KeyPairGenerator.getInstance("SLH-DSA", "BC");
        KeyPair kp = kpg.generateKeyPair();

        PKCS8EncodedKeySpec privSpec = kf.getKeySpec(kp.getPrivate(), PKCS8EncodedKeySpec.class);

        assertTrue(Arrays.areEqual(kp.getPrivate().getEncoded(), privSpec.getEncoded()));
        
        X509EncodedKeySpec pubSpec = kf.getKeySpec(kp.getPublic(), X509EncodedKeySpec.class);

        assertTrue(Arrays.areEqual(kp.getPublic().getEncoded(), pubSpec.getEncoded()));
    }

    public void testKeyPairEncoding()
        throws Exception
    {
        kf = KeyFactory.getInstance("HASH-SLH-DSA", "BC");

        SLHDSAParameterSpec[] params =
            {
                SLHDSAParameterSpec.slh_dsa_sha2_128s,
                SLHDSAParameterSpec.slh_dsa_sha2_128f,
                SLHDSAParameterSpec.slh_dsa_sha2_192s,
                SLHDSAParameterSpec.slh_dsa_sha2_192f,
                SLHDSAParameterSpec.slh_dsa_sha2_256s,
                SLHDSAParameterSpec.slh_dsa_sha2_256f,

                SLHDSAParameterSpec.slh_dsa_shake_128s,
                SLHDSAParameterSpec.slh_dsa_shake_128f,
                SLHDSAParameterSpec.slh_dsa_shake_192s,
                SLHDSAParameterSpec.slh_dsa_shake_192f,
                SLHDSAParameterSpec.slh_dsa_shake_256s,
                SLHDSAParameterSpec.slh_dsa_shake_256f,

                SLHDSAParameterSpec.slh_dsa_sha2_128s_with_sha256,
                SLHDSAParameterSpec.slh_dsa_sha2_128f_with_sha256,
                SLHDSAParameterSpec.slh_dsa_sha2_192s_with_sha512,
                SLHDSAParameterSpec.slh_dsa_sha2_192f_with_sha512,
                SLHDSAParameterSpec.slh_dsa_sha2_256s_with_sha512,
                SLHDSAParameterSpec.slh_dsa_sha2_256f_with_sha512,

                SLHDSAParameterSpec.slh_dsa_shake_128s_with_shake128,
                SLHDSAParameterSpec.slh_dsa_shake_128f_with_shake128,
                SLHDSAParameterSpec.slh_dsa_shake_192s_with_shake256,
                SLHDSAParameterSpec.slh_dsa_shake_192f_with_shake256,
                SLHDSAParameterSpec.slh_dsa_shake_256s_with_shake256,
                SLHDSAParameterSpec.slh_dsa_shake_256f_with_shake256,
            };
        
        // expected object identifiers
        ASN1ObjectIdentifier[] oids =
            {
                NISTObjectIdentifiers.id_slh_dsa_sha2_128s,
                NISTObjectIdentifiers.id_slh_dsa_sha2_128f,
                NISTObjectIdentifiers.id_slh_dsa_sha2_192s,
                NISTObjectIdentifiers.id_slh_dsa_sha2_192f,
                NISTObjectIdentifiers.id_slh_dsa_sha2_256s,
                NISTObjectIdentifiers.id_slh_dsa_sha2_256f,
                NISTObjectIdentifiers.id_slh_dsa_shake_128s,
                NISTObjectIdentifiers.id_slh_dsa_shake_128f,
                NISTObjectIdentifiers.id_slh_dsa_shake_192s,
                NISTObjectIdentifiers.id_slh_dsa_shake_192f,
                NISTObjectIdentifiers.id_slh_dsa_shake_256s,
                NISTObjectIdentifiers.id_slh_dsa_shake_256f,
                NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128s_with_sha256,
                NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128f_with_sha256,
                NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192s_with_sha512,
                NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192f_with_sha512,
                NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256s_with_sha512,
                NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256f_with_sha512,
                NISTObjectIdentifiers.id_hash_slh_dsa_shake_128s_with_shake128,
                NISTObjectIdentifiers.id_hash_slh_dsa_shake_128f_with_shake128,
                NISTObjectIdentifiers.id_hash_slh_dsa_shake_192s_with_shake256,
                NISTObjectIdentifiers.id_hash_slh_dsa_shake_192f_with_shake256,
                NISTObjectIdentifiers.id_hash_slh_dsa_shake_256s_with_shake256,
                NISTObjectIdentifiers.id_hash_slh_dsa_shake_256f_with_shake256
            };
        
        kpg = KeyPairGenerator.getInstance("HASH-SLH-DSA", "BC");
        
        for (int i = 0; i != params.length; i++)
        {
            kpg.initialize(params[i], new SecureRandom());
            KeyPair keyPair = kpg.generateKeyPair();
            performKeyPairEncodingTest(keyPair);
            assertEquals(oids[i], SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded()).getAlgorithm().getAlgorithm());
        }
    }

}
