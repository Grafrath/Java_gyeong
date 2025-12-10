async function updateOrderOnServer() {
    const currentOrder = Array.from(todoList.children).map((li, index) => {
        const id = parseInt(li.dataset.id);
        const todo = todos.find(t => t.id === id);
        
        return {
            id: id,
            title: todo.title,
            done: todo.done,
            itemOrder: index + 1
        };
    });

    try {
        const response = await fetch("http://localhost:8080/todo/updateOrder", {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(currentOrder)
        });

        if (!response.ok) throw new Error('순서 업데이트 실패');

        const result = await response.json();[]
        renderTodoList(result.data);
    } catch (error) {
        console.error("순서 업데이트 중 오류:", error);
        alert("순서 업데이트에 실패했습니다. 목록을 새로고침합니다.");
        loadTodos();
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const todoInput = document.querySelector("#todo-input");
    const addBtn = document.querySelector("#add-btn");
    const todoList = document.querySelector("#todo-list");
    const emptyText = document.querySelector("#empty-text");
    const errorMessage = document.querySelector("#error-message");

    let todos = []
    let sortableInstance = null;

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

            span.addEventListener("dblclick", () => {
                startEdit(li, span, todo);
            });

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

        if (sortableInstance) {
            sortableInstance.destroy();
        }

        sortableInstance = new Sortable(todoList, {
            animation: 150,
            handle: '.todo-left',
            onEnd: function (evt) {
                updateOrderOnServer();
            },
        });
    }

    async function loadTodos() {
        try {
            const response = await fetch("http://localhost:8080/todo/getAll");
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
        const currentTodo = todos.find(t => t.id === id);
        
        if (!currentTodo) {
            console.error("업데이트할 항목을 찾을 수 없습니다.");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/todo/updateTodo", {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    id: id,
                    title: currentTodo.title,
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

    function finishEdit(listItem, textSpan, editInput, todoItem) {
        const newTitle = editInput.value.trim();

        if (newTitle === "" || newTitle === todoItem.title) {
            textSpan.textContent = todoItem.title;
        } else {
            textSpan.textContent = newTitle;
            updateTodoTitle(todoItem.id, newTitle, todoItem.done);
        }

        editInput.remove();
        textSpan.style.display = 'inline';
    }

    function startEdit(listItem, textSpan, todoItem) {
        const editInput = document.createElement('input');
        editInput.type = 'text';
        editInput.className = 'edit-input';
        editInput.value = todoItem.title;

        textSpan.style.display = 'none';
        textSpan.parentNode.insertBefore(editInput, textSpan);

        editInput.focus();

        editInput.addEventListener('blur', () => {
            finishEdit(listItem, textSpan, editInput, todoItem);
        });

        editInput.addEventListener('keydown', (e) => {
            if (e.key === 'Enter') {
                editInput.blur();
            }
        });
    }

    async function updateTodoTitle(id, title, done) {
        try {
            const response = await fetch("http://localhost:8080/todo/updateTodo", {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    id: id,
                    title: title,
                    done: done
                })
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.error || '제목 업데이트에 실패했습니다.');
            }

            const result = await response.json();

            if (result.data) {
                renderTodoList(result.data);
            }
        } catch (error) {
            console.error("제목 업데이트 중 오류 발생:", error);
            alert(`업데이트 오류: ${error.message}`);
            loadTodos();
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