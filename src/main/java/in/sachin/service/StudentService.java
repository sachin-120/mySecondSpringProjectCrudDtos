package in.sachin.service;

import in.sachin.dto.StudentRequestDto;
import in.sachin.dto.StudentResponseDto;
import in.sachin.entity.StudentEntity;
import in.sachin.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //--------------------------------------//

    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {

        StudentEntity studentEntity=new StudentEntity();

        studentEntity.setName(studentRequestDto.getName());
        studentEntity.setEmail(studentRequestDto.getEmail());
        studentEntity.setRollNo(studentRequestDto.getRollNo());
        studentEntity.setCourse(studentRequestDto.getCourse());
        studentEntity.setMarks(studentRequestDto.getMarks());
        studentEntity.setAge(studentRequestDto.getAge());

        StudentEntity savedStudent=studentRepository.save(studentEntity);

        StudentResponseDto responseDto=new StudentResponseDto();

        responseDto.setName(savedStudent.getName());
        responseDto.setAge(savedStudent.getAge());
        responseDto.setCourse(savedStudent.getCourse());
        responseDto.setMarks(savedStudent.getMarks());
        responseDto.setRollNo(savedStudent.getRollNo());
        responseDto.setEmail(savedStudent.getEmail());

        return responseDto;
    }

    //---------------------------------//

    public List<StudentResponseDto> getAllStudent() {
        List<StudentEntity> studentEntity = studentRepository.findAll();

        List<StudentResponseDto> reponseList=new ArrayList<>();

        for(StudentEntity s1:studentEntity){

            StudentResponseDto responseDto=new StudentResponseDto();

            responseDto.setRollNo(s1.getRollNo());
            responseDto.setMarks(s1.getMarks());
            responseDto.setAge(s1.getAge());
            responseDto.setCourse(s1.getCourse());
            responseDto.setName(s1.getName());

            reponseList.add(responseDto);
        }

        return reponseList;
    }

    //---------------------------------------//

    public StudentResponseDto getElementById(Long id) {

        Optional<StudentEntity> optionalStudent =
                studentRepository.findById(id);

        if (optionalStudent.isEmpty()) {
            return null;
        }

        StudentEntity student = optionalStudent.get();

        StudentResponseDto responseDto =
                new StudentResponseDto();

        responseDto.setName(student.getName());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setCourse(student.getCourse());
        responseDto.setMarks(student.getMarks());
        responseDto.setAge(student.getAge());

        return responseDto;
    }

    //---------------------------------------//

    public StudentResponseDto update(StudentRequestDto student, Long id) {

        StudentEntity existingStudent =
                studentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Student not found"));

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setRollNo(student.getRollNo());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setMarks(student.getMarks());
        existingStudent.setAge(student.getAge());

        StudentEntity updatedStudent=studentRepository.save(existingStudent);

        StudentResponseDto responseDto=new StudentResponseDto();

        responseDto.setName(updatedStudent.getName());
        responseDto.setRollNo(updatedStudent.getRollNo());
        responseDto.setCourse(updatedStudent.getCourse());
        responseDto.setMarks(updatedStudent.getMarks());
        responseDto.setAge(updatedStudent.getAge());

        return responseDto;
    }

    //---------------------------------------//


    public void deleteElement(Long id) {
        studentRepository.deleteById(id);
    }
}