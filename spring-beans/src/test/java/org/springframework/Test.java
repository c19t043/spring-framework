package org.springframework;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class Test {

	@org.junit.Test
	public void test1(){
		//获取资源
		ClassPathResource resource = new ClassPathResource("bean.xml"); // <1>
		//获取 BeanFactory
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory(); // <2>
		//根据新建的 BeanFactory 创建一个 BeanDefinitionReader 对象，该 Reader 对象为资源的解析器
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory); // <3>
		//装载资源
		reader.loadBeanDefinitions(resource); // <4>

		//整个过程就分为三个步骤：资源定位、装载、注册

		//资源定位。我们一般用外部资源来描述 Bean 对象，所以在初始化 IoC 容器的第一步就是需要定位这个外部资源。

		//装载。装载就是 BeanDefinition 的载入。
		// BeanDefinitionReader 读取、解析 Resource 资源，也就是将用户定义的 Bean 表示成 IoC 容器的内部数据结构：BeanDefinition 。
		//在 IoC 容器内部维护着一个 BeanDefinition Map 的数据结构
		//在配置文件中每一个 <bean> 都对应着一个 BeanDefinition 对象。

		//注册。向 IoC 容器注册在第二步解析好的 BeanDefinition，这个过程是通过 BeanDefinitionRegistry 接口来实现的。
		//在 IoC 容器内部其实是将第二个过程解析得到的 BeanDefinition 注入到一个 HashMap 容器中，
		// IoC 容器就是通过这个 HashMap 来维护这些 BeanDefinition 的。

		//在这里需要注意的一点是这个过程并没有完成依赖注入，
		// 依赖注册是发生在应用第一次调用 #getBean(...) 方法，向容器索要 Bean 时。
		//当然我们可以通过设置预处理，
		// 即对某个 Bean 设置 lazyinit = false 属性，那么这个 Bean 的依赖注入就会在容器初始化的时候完成。
	}
}
