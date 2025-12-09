document.addEventListener("DOMContentLoaded", () => {
    const input = document.querySelector("#todo-input");
    const button = document.querySelector("#add-btn");
    const todoList = document.querySelector("#todo-list");
    const emptyText = document.querySelector("#empty-text");
    
    if (input && !input.hasAttribute("placeholder")) {
        input.setAttribute("placeholder", "할 일을 입력하세요");
    }

    todos.forEach(todo => {
        const li = document.createElement("li");
        li.className = "todo-item";

        const left = document.createElement("div");
        left.className = "todo-left"
        
        const checkbox = document.createElement("input");
        checkbox.type="checkbox";
        checkbox.checked = todo.done;

        const span = document.createElement("span")
        span.className = "todo-text";
        span.textContent = todo.title;

        if (todo.done) {
            span.classList.add("done");
        }

        checkbox.addEventListener("change", () => {
            span.classList.toggle("done");
        })

        left.appendChild(checkbox);
        left.appendChild(span)

        const delbtn = document.createElement("button");
        delbtn.className = "del-btn";
        delbtn.textContent = "삭제";

        delbtn.addEventListener("click", () => {});

        li.appendChild(left);
        li.appendChild(delbtn);

        todoList.appendChild(li);
    });
    
    async function addTodo() {
        const text = input.value.trim();
        
        if (text === "") {
            return alert("내용을 입력하세요.");
        }

        const response = await fetch("http://localhost:8080/todo/createTodo",
            {
                method : "POST",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify({title:text})
                //JSON.stringify(): js객체를 json문자열로 변환해준다
            }
        );

        const result = await response.json();
        console.log(result.data);

        input.value = "";
        
    }
    
    input.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            addTodo();
        }});
    button.addEventListener("click", addTodo);
});