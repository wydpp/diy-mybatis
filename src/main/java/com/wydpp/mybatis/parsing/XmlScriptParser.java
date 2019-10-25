package com.wydpp.mybatis.parsing;

import com.wydpp.mybatis.mapping.*;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Auther: duanpp
 * @Date: 2019/10/25 15:59
 * @Description:
 */
public class XmlScriptParser {

    private boolean isDynamic = false;

    private Map<String,NodeHandler> nodeHandlerMap = new HashMap<>();

    public XmlScriptParser(){
        nodeHandlerMap.put("if",new IfNodeHandler());
    }

    /**
     * 解析标签 select/update/delete
     * @param element
     * @return
     */
    public SqlSource parseScriptNode(Element element){
        MixedSqlNode sqlNode = parseDynamicTags(element);
        SqlSource sqlSource;
        if(isDynamic){
            sqlSource = new DynamicSqlSource(sqlNode);
        }else{
            sqlSource = new RawSqlSource(sqlNode);
        }
        return sqlSource;
    }

    /**
     * 解析sql脚本
     * @param element
     * @return
     */
    private MixedSqlNode parseDynamicTags(Element element){
        List<SqlNode> contents = new ArrayList<>();
        int nodeCount = element.nodeCount();
        for(int i=0;i<nodeCount;i++){
            Node node = element.node(i);
            if(node instanceof Text){
                String sqlText = node.getText().trim();
                if(sqlText == null || sqlText.equals("")){
                    continue;
                }
                TextSqlNode sqlNode = new TextSqlNode(sqlText);
                contents.add(sqlNode);
            }else if(node instanceof Element){
                // if\where\foreach等动态sql子标签就需要在这处理
                // 根据标签名称，封装到不同的节点信息
                Element nodeToHandle = (Element)node;
                String nodeName = nodeToHandle.getName().toLowerCase();
                System.out.println(nodeName);

            }
        }
        return new MixedSqlNode(contents);
    }



    private interface NodeHandler {
        void handleNode(Element nodeToHandle, List<SqlNode> targetContents);
    }

    /**
     * 解析if标签
     */
    private class IfNodeHandler implements NodeHandler{

        @Override
        public void handleNode(Element nodeToHandle, List<SqlNode> targetContents) {
            MixedSqlNode rootSqlNode = parseDynamicTags(nodeToHandle);
            String test = nodeToHandle.attributeValue("test");
            //需要判断入参是否符合test的判断
            //1.入参哪里来？--
            //2.如何判断？--ognl
        }
    }

}