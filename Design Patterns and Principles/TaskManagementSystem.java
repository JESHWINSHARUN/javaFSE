class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    public void display() {
        System.out.println(taskId + " " + taskName + " " + status);
    }
}

public class TaskManagementSystem {

    private Task head = null;

    // Add Task
    public void addTask(int id, String name, String status) {
        Task newTask = new Task(id, name, status);

        if (head == null) {
            head = newTask;
        } else {
            Task temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newTask;
        }

        System.out.println("Task Added Successfully.");
    }

    // Search Task
    public void searchTask(int id) {
        Task temp = head;

        while (temp != null) {
            if (temp.taskId == id) {
                System.out.println("Task Found:");
                temp.display();
                return;
            }
            temp = temp.next;
        }

        System.out.println("Task Not Found.");
    }

    // Traverse Tasks
    public void traverseTasks() {
        if (head == null) {
            System.out.println("No Tasks Available.");
            return;
        }

        System.out.println("\nTask List:");
        Task temp = head;

        while (temp != null) {
            temp.display();
            temp = temp.next;
        }
    }

    // Delete Task
    public void deleteTask(int id) {

        if (head == null) {
            System.out.println("List is Empty.");
            return;
        }

        if (head.taskId == id) {
            head = head.next;
            System.out.println("Task Deleted Successfully.");
            return;
        }

        Task temp = head;

        while (temp.next != null && temp.next.taskId != id) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Task Not Found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Task Deleted Successfully.");
        }
    }

    public static void main(String[] args) {

        TaskManagementSystem list = new TaskManagementSystem();

        list.addTask(101, "Design UI", "Pending");
        list.addTask(102, "Develop Backend", "In Progress");
        list.addTask(103, "Testing", "Pending");

        list.traverseTasks();

        list.searchTask(102);

        list.deleteTask(101);

        list.traverseTasks();
    }
}