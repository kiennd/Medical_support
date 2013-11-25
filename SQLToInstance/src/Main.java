import java.io.File;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVSaver;
import weka.experiment.InstanceQuery;



public class Main {
	public static void main(String[] args) throws Exception {
		InstanceQuery query = new InstanceQuery();
		query.setUsername("root");
		query.setPassword("");
		query.setQuery("select `0314_GOT_(AST)` ,`0312_Bilirubin_Total` ,`0309_Triglycerid_(TG)` "
				+ ",`0305_Albumin` ,`0302_Ure` ,`0325_Cl-` ,`0325_K+` ,`0325_Na+` ,`0307_HDL-C` "
				+ ",`0315_GPT_(_ALT_)` ,`0306_Cholesterol_(TC)` ,`0313_Bilirubin_Direct` ,`0301_Glucose` "
				+ ",`0327_Ca_(Toan_phan)` ,`0308_LDL-C` ,`0337_GGT_(Gamma_GT)` ,`0303_Creatinin` "
				+ ",`0310_Axit_Uric` ,`0304_Protein` ,`0319_CK` ,`0320_CK-MB` ,`0371_BNP` "
				+ ",`020109_Protein_nieu_(pr24)` ,`0350_Cyclosporin_C2` ,`020101_BIL_(Bilirubin)` "
				+ ",`020101_BLD_(Hong_cau)` ,`020101_GLU_(Glucose)` ,`020101_KET_(Ketone)` "
				+ ",`020101_LEU_(Bach_cau)` ,`020101_NIT_(Nitrit)` ,`020101_pH` ,`020101_PRO_(Protein)` "
				+ ",`020101_SG_(Ty_trong)` ,`020101_UBG_(Urobilinogen)` ,`0349_Cyclosporin_C0` ,`0348_HbA1c%` "
				+ ",`0352_Folate` ,`0347_Insulin` ,`0351_Vitamin_B12` ,`0353_Ferritin` ,`0321_LDH` "
				+ ",`0318_Amylase_huyet_thanh` ,`0362_Pre-Albumin2` ,`0368_HS-CRP` ,`0329_Magnesium` "
				+ ",`0375_Amoniac_(NH3)` ,`0355_Testosteron` ,`0335_?FP_(AFP)` ,`020204_Phan_ung_Pandy` "
				+ ",`020203_cl-_(dich)` ,`02020103_Protein_dich` ,`020205_Nonne-Appelt` ,`02020101_glucose_(dich)` "
				+ ",`020206_Na+_(dich)` ,`020102_Amylase_nieu` ,`020202_Phan_ung_Rivalta` ,`020207_LDH_(dich)` "
				+ ",`020104_Creatinin_nieu` ,`0343_CA19-9` ,`0376_BE(B)` ,`0376_BEecf` ,`0376_Ca++` ,`0376_HCO3-` "
				+ ",`0376_HCO3std` ,`0376_Hct` ,`0376_K+` ,`0376_Na+` ,`0376_pCO2` ,`0376_pH` ,`0376_pO2` ,`0376_SO2c` "
				+ ",`0376_TCO2` ,`0376_Temp` ,`0376_THbc` ,`0342_CA125` ,`0336_CEA` ,`0382_Pro-GRP` ,`0381_Cyfra_211` "
				+ ",`0340_PSA_total` ,`0383_SCC` ,`0359_Troponin_I` ,`0330_Phospho` ,`0364_IgA` ,`0377_C_peptide` "
				+ ",`0328_Sat_(Fe)` ,`020108_Microalbumin_nieu_(micn)` ,`0344_CA15-3` ,`0365_Cortisol` ,`0339_TSH` "
				+ ",`0338_FT3_(T3_free)` ,`0333_FT4_(T4_free)` ,`0346_?_hCG_trong_mau` ,`0380_Anti-CCP` ,`0370_Anti_Tg` "
				+ ",`0341_PSA_Free` ,`0376_A-aDO2` ,`0360_CRP_(C_Reactive_Protein)` ,`0378_Homocysteine` , `0366_Tacrolimus`"
				+ ",`chandoan` from dulieu"
				+ " inner join thongtinbn on dulieu.mabenhnhan = thongtinbn.mabenhnhan limit 2000");
		// if your data is sparse, then you can say so, too:
		// query.setSparseData(true);
		Instances data = query.retrieveInstances();
//		CSVSaver saver = new CSVSaver();
//		saver.setInstances(data);
//		saver.setFile(new File("a.csv"));
//		saver.writeBatch();
		System.out.println("abc");
//		EuclideanDistance eu=  new EuclideanDistance(data);
//		for(int i=2;i<data.numInstances();i++){
			System.out.println(data.instance(1));
//		}
//			
		data.setClassIndex(data.numAttributes() - 1); 
		System.out.println("load done");
		J48 j48 = new J48();
        FilteredClassifier fc = new FilteredClassifier();
        fc.setClassifier(j48);
        fc.buildClassifier(data);
        Instance ins = data.instance(data.numInstances()-1);
        double pred = fc.classifyInstance(ins);
        System.out.println("pred= " + pred);
        System.out.println(". predicted value: "
                + data.classAttribute().value((int) pred));
        System.out.println(data.classAttribute().value((int) pred));
        
//		System.out.println("distance: "+eu.distance(data.instance(9),data.instance(i)));
	}
}
