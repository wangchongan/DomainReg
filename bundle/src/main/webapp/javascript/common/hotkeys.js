// JavaScript Document
var hotkeyObjArray= new Array();
var cellInputID= new Array();
document.onkeydown = hotkeyFun;
/*
函数名：AddHotkeyBtnEven
作者：杨宏涛
时间：2008-12-26
联系：E-mail:goyangtao123@163.com Blog:http://blog.163.com/goyangtao123
作用：按键盘热键执行函数
性质：用户调用函数
参数：KeyCode 快捷键组合，FunName 函数名
示例：AddHotkeyEven("Shift+A","alert('你按shift+d了')")
*/
function AddHotkeyEven(KeyCode,FunName){
 
 if(KeyCode.toString().indexOf("++")>-1){
 alert("快捷键【"+KeyCode.toString()+"】设置有错误！")
 return ;
 }
 var keyObj=new Object();
 keyObj.keyCode=KeyCode;
 keyObj.funName=FunName;
 hotkeyObjArray.push(keyObj);
 
}
/*
函数名：AddHotkeyBtnEven
作者：杨宏涛
时间：2008-12-26
联系：E-mail:goyangtao123@163.com Blog:http://blog.163.com/goyangtao123
作用：按键盘热键模拟单击某一个按钮
性质：用户调用函数
参数：KeyCode 快捷键组合，BtnID 按钮的ID
示例：AddHotkeyEven("Shift+A","btn2")
*/
function AddHotkeyBtnEven(KeyCode,BtnID){
 if(BtnID.toString().indexOf("(")>-1||BtnID.toString().indexOf(")")>-1){
  alert("函数AddHotkeyBtnEven的BtnID参数不允许有括号等特殊字符！")
  return ;
 }
 var btnClick="document.getElementById('"+BtnID+"').click()"
 AddHotkeyEven(KeyCode,btnClick)
}
/*
函数名：UpDownRightLiftKey
作者：杨宏涛
时间：2008-12-26
联系：E-mail:goyangtao123@163.com Blog:http://blog.163.com/goyangtao123
作用：按键盘上的上下左右键可以让有模板列GridView的文本框上下左右获得焦点
性质：用户调用函数
参数：cellInputIDArray为模板列的文本ID数组，切忌顺序不可以乱。
示例：
var cellInput= new Array();
cellInput.push("JGBH");
cellInput.push("cID");
cellInput.push("cAmoName");
cellInput.push("iPay");
cellInput.push("cbxm");
cellInput.push("cDataFrom");
UpDownRightLiftKey(cellInput)
*/
function UpDownRightLiftKey(cellInputIDArray){
 if(cellInputIDArray!=null&&cellInputIDArray.length>0){
 cellInputID=cellInputIDArray;
 AddHotkeyEven("↑","InputUp()");
 AddHotkeyEven("↓","InputDown()");
 AddHotkeyEven("→","InputRight()");
 AddHotkeyEven("←","InputLeft()");
 }
}
/*
函数名：hotkeyFun
作者：杨宏涛
时间：2008-12-26
联系：E-mail:goyangtao123@163.com Blog:http://blog.163.com/goyangtao123
作用：响应键盘事件，本程序的核心代码
性质：系统调用函数
*/
function hotkeyFun(){
 for(var i=0;i<hotkeyObjArray.length;i++){
  var execStr="";
  var keyObj=new Object();
  keyObj=hotkeyObjArray;
  var keyCodeStr=keyObj.keyCode;
  var keyCodeArray =keyCodeStr.split("+");
  
  for(var j=0;j<keyCodeArray.length;j++){
   if(keyCodeArray[j].toLowerCase()=="ctrl"){
   
   execStr+="(event.ctrlKey)";
   
   }else if(keyCodeArray[j].toLowerCase()=="alt"){
   
   execStr+="(event.altKey)";
   
   }else if(keyCodeArray[j].toLowerCase()=="shift"){
   
   execStr+="(event.shiftKey)";
   }else {
    var keyASCII=getASCII(keyCodeArray[j].toLowerCase());
    execStr+="(window.event.keyCode=="+keyASCII+")";
   }
  }
  for(var j=0;j<execStr.toString().length;j++){
  execStr=execStr.replace(")(",")&&(");
  }
  //alert(execStr);
  execStr="if("+execStr+"){"+keyObj.funName+"}";
  try{
  eval(execStr);
  }catch(err){
  alert("请检查函数【"+keyObj.funName+"】语法是否正确!\n错误信息：\n "+err.description)
  }
 }
}
function manageId(strId){
 if(parseInt(strId)<10){
  return "0"+strId;
 }else{
  return strId;
 }
}
/*
函数名：FormatNum
作者：杨宏涛
时间：2008-12-29
联系：E-mail:goyangtao123@163.com Blog:http://blog.163.com/goyangtao123
作用：格式化数字
性质：用户调用函数
参数：number用户传递的数字参数，style格式化的样式
示例：
FormatNum(24.55,"000000.000")
处理结果：000024.550
*/
function FormatNum(number,style){
 if(isNaN(number)){
 alert("需要格式化的数字不是有效数字【"+number+"】");
 return number;
 }
 for(var j=0;j<style.toString().length;j++){
  var dotNum=0;
  if(style.charAt(j)!="0"&&style.charAt(j)!="."){
  alert("格式化样式错误！【"+style+"】");
  return number;
  }else{
   if(style[j]=="."){
    dotNum++;
    if(dotNum>1){
    alert("格式化样式小数点错误!【"+style+"】");
    return number;
    }
   }
  }
 }
 var resNumber;
 var styleDec=style.split(".")[1];//小数部分
 var styleInt=style.split(".")[0];//整数部分
 if(styleDec!=null&&typeof(styleDec) != "undefined"&&styleDec.toString().length>0){
  resNumber=parseFloat(number).toFixed(styleDec.toString().length);
 }else{
  resNumber=parseInt(number);
  resNumber=parseFloat(resNumber.toString());
 }
 var ZoreNum="";
 if(parseFloat(1+styleInt)>resNumber){
  var styleIntLength=styleInt.toString().length;
  var UserIntLength=resNumber.toString().split(".")[0].length;
  for(var i=0;i<(styleIntLength-UserIntLength);i++){
  ZoreNum+="0";
  }
 }
 return ZoreNum+resNumber.toString();
}
function InputUp(){
 if(document.activeElement.tagName=="INPUT"){
 var inputCurrentID=document.activeElement.id;
 
 var inputCurrentIDArray=inputCurrentID.split("_");
 var rowsIndex=parseInt(inputCurrentIDArray[1].replace("ctl",""))-1;
  try{
  document.getElementById(inputCurrentIDArray[0]+"_ctl"+manageId(rowsIndex)+"_"+inputCurrentIDArray[2]).focus();
  }catch(err){
  document.getElementById(inputCurrentID).focus();
  }
 }
}
function InputDown(){
 if(document.activeElement.tagName=="INPUT"){
 var inputCurrentID=document.activeElement.id;
 
 var inputCurrentIDArray=inputCurrentID.split("_");
 var rowsIndex=parseInt(inputCurrentIDArray[1].replace("ctl",""))+1;
  try{
  document.getElementById(inputCurrentIDArray[0]+"_ctl"+manageId(rowsIndex)+"_"+inputCurrentIDArray[2]).focus();
  }catch(err){
  document.getElementById(inputCurrentID).focus();
  }
 }
}
function InputRight(){
 if(document.activeElement.tagName=="INPUT"){
  var inputCurrentID=document.activeElement.id;
  
  var inputCurrentIDArray=inputCurrentID.split("_");
  var cellIndex=0;
  for(var i=0;i<cellInputID.length;i++){
   if(cellInputID.toString()==inputCurrentIDArray[2].toString()){
   cellIndex=i;
   }
  }
  try{
  
   document.getElementById(inputCurrentIDArray[0]+"_"+inputCurrentIDArray[1]+"_"+cellInputID[cellIndex+1]).focus();
  }catch(err){
   document.getElementById(inputCurrentID).focus();
  }
 }
}
function InputLeft(){
 if(document.activeElement.tagName=="INPUT"){
  var inputCurrentID=document.activeElement.id;
  
  var inputCurrentIDArray=inputCurrentID.split("_");
  var cellIndex=0;
  for(var i=0;i<cellInputID.length;i++){
   if(cellInputID.toString()==inputCurrentIDArray[2].toString()){
   cellIndex=i;
   }
  }
  try{
   document.getElementById(inputCurrentIDArray[0]+"_"+inputCurrentIDArray[1]+"_"+cellInputID[cellIndex-1]).focus();
  }catch(err){
   document.getElementById(inputCurrentID).focus();
  }
 }
}
/*
函数名：getASCII
作者：陈翔实
时间：2008-12-26
联系：
作用：通过参数键盘键名获取其ascii的值
性质：系统调用函数
*/
function getASCII(keyName)
{
 var keyName;
 var code=97;
          
         switch(keyName)
      {
          
          
          case 'a':
    return code-32
    break
          case 'b':
    return code-31
    break
          case 'c':
    return code-30
    break
          case 'd':
    return code-29
    break
          case 'e': 
    return code-28
    break
          case 'f':
    return code-27
    break
          case 'g':
    return code-26
    break
          case 'h':
    return code-25
    break
          case 'i': 
    return code-24
    break
          case 'j':
    return code-23
    break
          case 'k':
    return code-22
    break
          case 'l':
    return code-21
    break
          case 'm':
    return code-20
    break
          case 'n':
    return code-19
    break
          case 'o':
    return code-18
    break
          case 'p':
    return code-17
    break
          case 'q':
    return code-16
    break
          case 'r':
    return code-15
    break
          case 's':
    return code-14
    break
          case 't':
    return code-13
    break
          case 'u':
    return code-12
    break
          case 'v':
    return code-11
    break
          case 'w':
    return code-10
    break
          case 'x':
    return code-9
    break
          case 'y':
    return code-8
    break
          case 'z':
    return code-7
    break
          
          case '0':
    return code-49
    break
          case '1':
    return code-48
    break
          case '2':
    return code-47
    break
          case '3':
    return code-46
    break
          case '4':
    return code-45
    break
          case '5':
    return code-44
    break
          case '6':
    return code-43
    break
          case '7':
    return code-42
    break
          case '8':
    return code-41
    break
          case '9':
    return code-40
    break
          
    case "~" :
    return 192
    break
    case "-" :
    return 189 
    break
    case "=" :
    return 187 
    break
    case "backspace" :
    return 8 
    break
    case "table" :
    return 9 
    break
    case "caps_lock" :
    return 20 
    break
    case "shift" :
    return 16
    break
    case "ctrl" :
    return 17
    break
    case "win_l" :
    return 91 
    break
    case "win_r" :
    return 92
    break
    case "alt" :
    return 18 
    break
    case "space" :
    return 32 
    break
    case "enter" :
    return 13 
    break
    case "option" :
    return 93 
    break
    case "[" :
    return 219 
    break
    case "]" :
    return 221
    break
    case "\\" :
    return 220
    break
    case ";" :
    return 186 
    break
    case "'" :
    return 222 
    break
    case "," :
    return 188 
    break
    case "." :
    return 190 
    break
    case "/" :
    return 191 
    break
    
    case "scroll_lock" :
    return 145 
    break
    case "pause" :
    return 19 
    break
    case "insert" :
    return 45 
    break
    case "home" :
    return 36 
    break
    case "page_up" :
    return 33 
    break
    case "delete" :
    return 46 
    break
    case "end" :
    return 35 
    break
    case "page_down" :
    return 34 
    break
          case "↑" :
    return 38
    break
    case "↓" :
    return 40
    break
    case "←" :
    return 37 
    break
          case "→" :
    return 39
    break
    
    
    case "esc":
    return 27
    break
    case "f1" :
    return 112
    break
    case "f2" :
    return 113
    break
    case "f3" :
    return 114
    break
    case "f4" :
    return 115
    break
    case "f5" :
    return 116
    break
    case "f6" :
    return 117
    break
    case "f7" :
    return 118
    break
    case "f8" :
    return 119
    break
    case "f9" :
    return 120
    break
    case "f10" :
    return 121
    break
    case "f11" :
    return 122
    break
    case "f12" :
    return 123
    break
          
    case "num_lock" :
    return 144 
    break
    case "/" :
    return 111 
    break
    case "*" :
    return 106 
    break
    case "-" :
    return 109 
    break
    case "+" :
    return 107 
    break
    case "num_." :
    return 110 
    break
    case "num_1" :
    return 97 
    break
    case "num_2" :
    return 98 
    break
    case "num_3" :
    return 99 
    break
    case "num_4" :
    return 100
    break
    case "num_5" :
    return 101 
    break
    case "num_6" :
    return 102 
    break
    case "num_7" :
    return 103 
    break
    case "num_8" :
    return 104 
    break
    case "num_9" :
    return 105 
    break
    case "num_0" :
    return 96 
    break
      }
}