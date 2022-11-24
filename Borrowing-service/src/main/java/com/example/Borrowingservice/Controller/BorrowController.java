package com.example.Borrowingservice.Controller;

import com.example.Borrowingservice.Dto.BorrowRequestDto;
import com.example.Borrowingservice.Dto.BorrowResponseDto;
import com.example.Borrowingservice.Service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @GetMapping
    public ResponseEntity<List<BorrowResponseDto>> getAll(){
        System.out.println("here");
        List<BorrowResponseDto> borrowServiceResponse = borrowService.getAll();
        return new ResponseEntity<>(borrowServiceResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody BorrowRequestDto borrowRequestDto){
        System.out.println(borrowRequestDto);
        borrowService.add(borrowRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{borrowingNumber}")
    public ResponseEntity<BorrowResponseDto> getOne(@PathVariable String borrowingNumber){
        return new ResponseEntity<>(borrowService.getOne(borrowingNumber), HttpStatus.OK);
    }
    @PutMapping("/{borrowingNumber}")
    public ResponseEntity<?> update(@PathVariable String borrowingNumber,@RequestBody BorrowRequestDto borrowRequestDto){
        borrowService.update(borrowingNumber, borrowRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{borrowingNumber}")
    public ResponseEntity<?> delete(@PathVariable String borrowingNumber){
        borrowService.delete(borrowingNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
