/**
 * 
 */

function changeSelect(s,no){
    form = document.join;
    sel = s[s.selectedIndex].value;
    dis = 1;
 
    if(sel=="user"){
        sel = "";
        dis = 0;
    }
 
    if(no==1){
        form.EmailDomain1.value = sel;
        form.EmailDomain1.disabled = dis;
    }else{
        form.EmailDomain2.value = sel;
        form.EmailDomain2.disabled = dis;
    }
}





