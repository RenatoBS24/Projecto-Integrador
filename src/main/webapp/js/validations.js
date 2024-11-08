function validate (){
    const input = document.querySelectorAll('input[type="text"]');
    const regex = /['";(){}<>]/g;
    input.forEach((input) => {
        input.addEventListener('input',function (){
            input.value = input.value.replace(regex,"");
        })
    })

}
function edit_or_delete_validate(name){
    let inputs = document.getElementsByName(name);
    const regex = /[a-zA-Z]/g;
    inputs.forEach(input =>{
        input.addEventListener('keypress',(event) =>{
            if(!regex.test(event.key)){
                event.preventDefault();
            }
        })
    })
}

function validate_numbers(){
    const inputs = document.querySelectorAll('input.validate');
    const regex = /[0-9]/g;
    inputs.forEach(input =>{
        input.addEventListener('keypress', (event) => {
            if(!regex.test(event.key)){
                event.preventDefault();
            }
        })
    })

}