document.addEventListener("DOMContentLoaded", () => {
    const todoInput = document.querySelector("#todo-input");
    const addBtn = document.querySelector("#add-btn");
    const todoList = document.querySelector("#todo-list");
    const emptyText = document.querySelector("#empty-text");
    const errorMessage = document.querySelector("#error-message");

    let todos = []

    function checkEmptyState() {
        if (todos.length === 0) {
            emptyText.style.display = 'block';
        } else {
            emptyText.style.display = 'none';
        }
    }

    function renderTodoList(newTodos) {
        todoList.innerHTML = "";
        todos = newTodos || [];

        checkEmptyState();
        
        todos.forEach(todo => {
            const li = document.createElement("li");
            li.className = "todo-item";
            li.dataset.id = todo.id;

            const left = document.createElement("div");
            left.className = "todo-left";

            const checkbox = document.createElement("input");
            checkbox.type = "checkbox"
            checkbox.checked = todo.done;

            const span = document.createElement("span");
            span.className = "todo-text";
            span.textContent = todo.title;

            if (todo.done) {
                span.classList.add("done");
            }

            checkbox.addEventListener("change", () => {
                span.classList.toggle("done");
                updateTodoStatus(todo.id, checkbox.checked);
            });

            left.appendChild(checkbox);
            left.appendChild(span);

            const delBtn = document.createElement("button");
            delBtn.className = "delete-btn";
            delBtn.textContent = "삭제";

            delBtn.addEventListener("click", () => {
                deleteTodo(todo.id);
            });

            li.appendChild(left);
            li.appendChild(delBtn);
            todoList.appendChild(li);
        });

    }

    async function loadTodos() {
        try {
            const response = await fetch("http://localhost:8080/todo/retrieveTodoList");
            const data = await response.json();

            if (data.data) {
                renderTodoList(data.data);
            }
        } catch (error) {
            console.error("할 일 목록을 불러오지 못했습니다:", error);
        }
    }

    async function addTodo() {
        const text = todoInput.value.trim();

        if (text === "") {
            console.warn("내용을 입력하세요.");
            if (errorMessage) errorMessage.style.display = 'block';
            return;
        }

        if (errorMessage) errorMessage.style.display = 'none';

        try {
            const response = await fetch("http://localhost:8080/todo/createTodo", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ title: text })
            });

            const result = await response.json();

            if (result.data) {
                renderTodoList(result.data);
                todoInput.value = "";
                todoInput.focus();
            }
        } catch (error) {
            console.error("할 일 추가 중 오류 발생:", error);
        }
    }

    async function updateTodoStatus(id, done) {
        try {
            const response = await fetch("http://localhost:8080/todo/updateTodo", {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    id: id,     // 업데이트 대상 ID 전송
                    done: done
                })
            });

            const result = await response.json();

            if (result.data) {
                renderTodoList(result.data);
            }
        } catch (error) {
            console.error("상태 업데이트 중 오류 발생:", error);
        }
    }

    async function deleteTodo(id) {
        try {
            const response = await fetch("http://localhost:8080/todo/deleteTodo", {
                method: "DELETE",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ id: id })
            });

            const result = await response.json();

            if (result.data) {
                renderTodoList(result.data);
            }
        } catch (error) {
            console.error("할 일 삭제 중 오류 발생:", error);
        }
    }

    todoInput.addEventListener("keydown", e => {
        if (e.key === "Enter") {
            addTodo();
        }
    })

    addBtn.addEventListener("click", addTodo);

    loadTodos();

});