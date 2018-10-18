package xml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ����XML�ĵ�
 * @author tarena
 *
 */
public class ParseXmlDemo {
	public static void main(String[] args) {
		List<Emp> list = new ArrayList<Emp>();
		/*
		 * ʹ��DOM����XML�Ĵ��²���
		 * 1.����SAXReader
		 * 2.ʹ��SAXReader��ȡָ����xml�ĵ�������
		 *   Document����
		 *   ��һ������DOM������ʱ����Դ�ĵط�����Ϊ
		 *   Ҫ�ֽ�XML�ĵ�ȫ����ȡ��ϲ���Document����
		 *   ��ʽ�������ڴ���
		 * 3.ͨ��Document��ȡ��Ԫ��
		 * 4.����XML�ĵ��Ľṹ�𼶻�ȡ��Ԫ���Դﵽ����
		 *   XML�ĵ���Ŀ��
		 */
		try {
			/*
			 * org.dom4j.io.SAXReader
			 * ���eclipse��ʾ�Ҳ�������࣬��ôԭ������
			 * û�е���dom4j��jar��
			 * 
			 * 1.����SAXReader
			 */
			SAXReader reader = new SAXReader();
			//2
//			Document doc = reader.read(new File("emplist.xml"));
			Document doc = reader.read(new FileInputStream("emplist.xml"));
			/*
			 * 3
			 * Document�ṩ�˻�ȡ��Ԫ�صķ�����
			 * Element getRootElement()
			 * 
			 * Element��ÿһ��ʵ�����ڱ�ʾXML�ĵ��е�һ��Ԫ�أ�һ�Ա�ǩ��
			 * Element�ṩ�˻�ȡ���ʾ��Ԫ�ص���ط������õ��У�
			 * 
			 * String getName()
			 * ��ȡ��ǰ��ǩ������
			 * 
			 * String getText()
			 * ��ȡ��ǰ��ǩ�м���ı�������:<a>��ȡ���ı�</a>
			 * 
			 * Element element(String name)
			 * ��ȡ��ǰ��ǩ��ָ�����ֵ��ֱ�ǩ
			 * 
			 * List elements()
			 * ��ȡ��ǰ��ǩ�������ӱ�ǩ
			 * 
			 * List elements(String name)
			 * ��ȡ��ǰ��ǩ������ͬ����ָ�������֣��ӱ�ǩ
			 */
			Element root = doc.getRootElement();
			/*
			 * ��ȡ����ǩ<list>�����е�<emp>��ǩ
			 */
			List<Element> empList = root.elements("emp");
			for(Element empEle:empList){
				/*
				 * ��ȡ<emp id="xx">��ǩ������id��Ӧ��ֵ
				 * Element�ṩ�˻�ȡ���Եķ�����
				 * Attribute attribute(String name)
				 * ��ȡָ�����ֵ�����
				 * 
				 * Attribute��ÿ��ʵ�����ڱ�ʾĳ����ǩ�е�һ������
				 * �����������÷�����
				 * String getName():��ȡ���Ե�����
				 * String getValue():��ȡ���Ե�ֵ
				 * 
				 * ElementҲ�ṩ�˿�ݻ�ȡ����ֵ�ķ�����
				 * String attributeValue(String name)
				 * ��ȡ��ǰ��ǩ��ָ�����ֶ�Ӧ���Ե�ֵ
				 */
				//��ȡID
//				Attribute attr = empEle.attribute("id");
//				int id = Integer.parseInt(attr.getValue());
				int id = Integer.parseInt(empEle.attributeValue("id"));
				//��ȡ����
				Element nameEle = empEle.element("name");
				String name = nameEle.getText();
				//��ȡ����
				int age = Integer.parseInt(empEle.elementText("age"));
				//��ȡ�Ա�
				String gender = empEle.elementText("gender");
				//��ȡ����
				int salary = Integer.parseInt(empEle.elementText("salary"));
				Emp emp = new Emp(id,name,age,gender,salary);
				list.add(emp);
			}//loop end
			System.out.println("������ϣ�");
			System.out.println("��ȡ��"+list.size()+"��Ա��");
			for(Emp e:list){
				System.out.println(e);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}









