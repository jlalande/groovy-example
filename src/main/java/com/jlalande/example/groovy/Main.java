package com.jlalande.example.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.MetaMethod;
import groovy.lang.MetaProperty;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;

public class Main {

  /**
   * @param args
   * @throws IOException
   * @throws CompilationFailedException
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public static void main(String[] args)
    throws CompilationFailedException,
      IOException,
      InstantiationException,
      IllegalAccessException
  {
    ClassLoader parent = Main.class.getClassLoader();
    GroovyClassLoader loader = new GroovyClassLoader(parent);

    Class<?> groovyClass = loader.parseClass(new File("src/main/groovy/example.groovy"));
    GroovyObject object = (GroovyObject) groovyClass.newInstance();

    System.out.println("===== CLASS =====");
    System.out.println(object.getClass().toString());

    System.out.println("===== METHODS =====");
    for (MetaMethod method : object.getMetaClass().getMetaMethods()) {
      System.out.println(method.getSignature());
    }

    System.out.println("===== PROPERTIES =====");
    for (MetaProperty property : object.getMetaClass().getProperties()) {
      System.out.println(property.toString());
    }

    if (object instanceof Script) {
      Script script = (Script) object;
      System.out.println(script.getName());
    }

  }
}
