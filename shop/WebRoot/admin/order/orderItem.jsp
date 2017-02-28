<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

    <table border="0" width="100%">
        <tr>
           <td>
                                          商品图片
           </td>
            <td>
                                         数量
           </td>
            <td>
                                       小计
           </td>
        </tr>
        <s:iterator var="orderItem" value="list">
        <tr>
           <td>
               <img width="40" height="40" src="${pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>"/>
           </td> 
            <td>
               <s:property value="#orderItem.count"/>
           </td>
            <td>
               <s:property value="#orderItem.subtotal"/>
           </td>
        </tr>
        </s:iterator>
    </table>
