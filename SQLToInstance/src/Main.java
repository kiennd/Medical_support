import java.io.File;
import java.text.AttributedCharacterIterator.Attribute;

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
		query.setPassword(" ");
		StringBuffer q = new StringBuffer("select");
		for (int i = 0; i < ConstantMedical.LABORATOR_CATEGORY.length; i++) {
			q.append("`"+ConstantMedical.LABORATOR_CATEGORY[i]+"`,");
		}
		q.append("`result` from tbllaborator"
				+ " inner join tbllaboratorform on tbllaboratorform.patientid = tbllaborator.patientid"
				+ "	and tbllaboratorform.count = tbllaborator.count"
				+ " limit 2000");
		
		query.setQuery(q.toString());
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
        
        for(int i = 0 ;i< ins.numAttributes();i++){
        	
        }
        
        double pred = fc.classifyInstance(ins);
        System.out.println("pred= " + pred);
        System.out.println(". predicted value: "
                + data.classAttribute().value((int) pred));
        System.out.println(data.classAttribute().value((int) pred));
        
//		System.out.println("distance: "+eu.distance(data.instance(9),data.instance(i)));
	}
}
