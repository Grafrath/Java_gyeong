document.addEventListener("DOMContentLoaded", () => {
    const input = document.querySelector("#todo-input");
    const button = document.querySelector("#add-btn");
    const todoList = document.querySelector("#todo-list");
    const emptyText = document.querySelector("#empty-text");
    
    if (input && !input.hasAttribute("placeholder")) {
        input.setAttribute("placeholder", "할 일을 입력하세요");
    }
    
    async function addTodo() {
        const text = input.value.trim();
        
        if (text === "") {
            return alert("내용을 입력하세요.");
        }

        const response = await fetch("http://localhost:8080/todo/createtodo",
            {
                method : "POST",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify({title:text})
                //JSON.stringify(): js객체를 json문자열로 변환해준다
            }
        );

        const result = await response.json();
        console.log(result.data);
        
        const li = document.createElement("li");
        li.textContent = text;
        
        todoList.appendChild(li);
        input.value = "";
        
        emptyText.style.display = "none";
    }
    
    input.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            addTodo();
        }});
    button.addEventListener("click", addTodo);
});