package in.sachin.controller;

import in.sachin.dto.StudentRequestDto;
import in.sachin.dto.StudentResponseDto;
import in.sachin.entity.StudentEntity;
import in.sachin.service.StudentService;
import org.hibernate.validator.constraints.IpAddress;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static jdk.internal.org.jline.utils.Colors.s;

@RestController
@RequestMapping("/students")
public class StudentController {


    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }



    @PostMapping("/student")
    public StudentResponseDto createStudent(@RequestBody StudentRequestDto studentRequestDto){
        StudentResponseDto studentEntity=studentService.createStudent(studentRequestDto);

        return studentEntity;
    }

    @GetMapping("/student")

    public List<StudentResponseDto> getAll(){
        List<StudentResponseDto> studentEntityList=studentService.getAllStudent();

        return studentEntityList;
    }

    @GetMapping("/student/{id}")
    public StudentResponseDto getElementId(@PathVariable Long id){
        StudentResponseDto students=studentService.getElementById(id);

        return students;
    }

    @PutMapping("/student/{id}")

    public StudentResponseDto updateStudent(@RequestBody StudentRequestDto student,@PathVariable Long id){
        StudentResponseDto studentEntity=studentService.update(student,id);

        return studentEntity;
    }

    @DeleteMapping("/student/{id}")

    public void deleteStudent(@PathVariable Long id){
        studentService.deleteElement(id);
    }



}
