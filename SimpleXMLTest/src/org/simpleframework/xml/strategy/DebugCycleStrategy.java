package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.NodeMap;

/**
 * Two Path Cycle Strategy
 * @author wf
 */
public class DebugCycleStrategy extends CycleStrategy {

  public DebugCycleStrategy(String id, String ref) {
    super(id, ref);
  }

  @Override
  public Value read(Type type, NodeMap node, Map map) throws Exception {
    System.out.println("reading " + type);
    Value value = super.read(type, node, map);
    return value;
  }
}
