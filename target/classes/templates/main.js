function validateForm(){
    var subject = document.getElementById("subj").value;
    if(subject ==""){
        document.getElementById("errorSub").innerHTML="Nhóm đối tượng không được bỏ trống";
        return false;
    }
}
