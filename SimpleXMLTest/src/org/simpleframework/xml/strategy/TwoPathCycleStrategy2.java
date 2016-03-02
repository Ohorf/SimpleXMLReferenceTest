package org.simpleframework.xml.strategy;

import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.stream.Node;
import org.simpleframework.xml.stream.NodeMap;

/**
 * Two Path Cycle Strategy
 * @author wf
 */
public class TwoPathCycleStrategy2 extends CycleStrategy {
  String id;
  String ref;
  public static boolean debug = true;
  public Map<String, Value> lookup = new HashMap<String, Value>();

  /**
   * create a twoPath Cycle Strategy
   * @param id
   * @param ref
   */
  public TwoPathCycleStrategy2(String id, String ref) {
    super(id, ref);
    this.id = id;
    this.ref = ref;
  }

  /**
   * show debug information
   * @param title
   * @param key
   * @param value
   */
  public void showDebug(String title, String key, Value value) {
    if (debug) {
      String id = "?";
      Object v = value;
      while ((v instanceof Value) && ((Value) v).isReference()) {
        v = ((Value) v).getValue();
      }
      if (v == null) {
        id = "null";
      } else {

        // FIXME - adapt to your code or comment out
        // if (v instanceof ModelElement) {
        // ModelElement me = (ModelElement) v;
        // id = me.getId();
        // }
      }
      System.err.println(title + ":" + key + "->" + v.getClass().getSimpleName() + ":" + value.getType().getSimpleName() + ":" + value.isReference() + ":" + id);
    }
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Value read(Type type, NodeMap node, Map map) throws Exception {
    Value value = null;
    Node refNode = node.get(ref);
    Node keyNode = node.get(id);
    try {
      value = super.read(type, node, map);
      if (keyNode != null) {
        String key = keyNode.getValue();
        if (value != null) {
          showDebug("putting id", key, value);
          lookup.put(key, value);
        } else {
          showDebug("looking up id?", key, value);
        }
      }
    } catch (CycleException ce) {
      if (ce.getMessage().contains("Invalid reference")) {
        if (refNode != null) {
          String key = refNode.getValue();
          if (lookup.containsKey(key)) {
            value = lookup.get(key);
            showDebug(" found ref", key, value);
          } else {
            System.out.println("could not find ref creating dummy" + key);
            return null;
//            Object dummy = type.getType().newInstance();
//            Method method = dummy.getClass().getMethod("dummyInit");
//            method.invoke(dummy);
//            value = new ObjectValue(type.getType());
//            value.setValue(dummy);
//            value = new Reference(dummy, type.getType());
          }
        }
      } else {
        throw ce;
      }
    }
    return value;
  }
}
