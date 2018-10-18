package xml;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * ʹ��DOM����XML�ĵ�
 * @author tarena
 *
 */
public class WriteXmlDemo {
	public static void main(String[] args){
		/*
		 * ����һ��XML�ĵ��Ĵ��²���
		 * 1.����һ��Documentʵ������ʾһ���հ��ĵ�
		 * 2.��Documentʵ������Ӹ�Ԫ��
		 * 3.����Ԥ�����ɵ�XML�ĵ��ṹ���Ԫ������
		 *   �����Ԫ���Լ���Ӧ����
		 * 4.����XmlWriter
		 * 5.ͨ��XmlWriter��Documentд��������XML�ĵ�
		 */
		List<Emp> list = new ArrayList<Emp>();
		list.add(new Emp(1,"����",22,"��",5000));
		list.add(new Emp(2,"����",23,"Ů",6000));
		list.add(new Emp(3,"����",24,"��",7000));
		list.add(new Emp(4,"����",25,"Ů",8000));
		list.add(new Emp(5,"Ǯ��",26,"��",9000));
		
		try {
			//1����һ���հ��ĵ�
			Document doc = DocumentHelper.createDocument();
			/*
			 * 2
			 * Document�ṩ����Ӹ�Ԫ�صķ�����
			 * Element addElement(String name)
			 * ��ǰ�ĵ�����Ӹ������ֵ�Ԫ�أ�����һ��
			 * �ĵ���ֻ����һ����Ԫ�أ����Ը÷���ֻ�ܵ���
			 * һ�Ρ��÷�������һ��Elementʵ������
			 * ʵ���ͱ�ʾ����ӵ�Ԫ�أ��Ա�������׷����Ԫ��
			 * �Ȳ�����
			 */
			Element root = doc.addElement("list");
			/*
			 * 3
			 * ��������ÿ��Empʵ����һ��<emp>Ԫ�ص��γ�
			 * ��ӵ���Ԫ��<list>��
			 */
			/**
			 * ��������ת�����ַ�����򵥵ķ��������ں���ƴ��һ�����ַ���
			 */
			for(Emp emp:list){
				//�����ǩ������Ա�ǩemp
				Element empEle = root.addElement("emp");
				//�������
				Element nameEle = empEle.addElement("name");
				nameEle.addText(emp.getName());
				//�������
				Element ageEle = empEle.addElement("age");
				ageEle.addText(emp.getAge()+"");
				//����Ա�
				Element genderEle = empEle.addElement("gender");
				genderEle.addText(emp.getGender());
				//��ӹ���
				Element salaryEle = empEle.addElement("salary");
				salaryEle.addText(emp.getSalary()+"");
				
				//���id�����ԣ�
				empEle.addAttribute("id",emp.getId()+"");
			}
			//4
			XMLWriter writer = new XMLWriter(
					new FileOutputStream("myemp.xml"),
					OutputFormat.createPrettyPrint()
			);
			//5
			writer.write(doc);
			System.out.println("д�����");
			writer.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
