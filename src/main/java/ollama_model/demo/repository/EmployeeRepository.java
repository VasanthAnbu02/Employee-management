package ollama_model.demo.repository;



import ollama_model.demo.model.Employee;


import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}