import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  AbstractControl, FormArray, FormBuilder, FormControl, FormGroup,
  ReactiveFormsModule, ValidationErrors, Validators
} from '@angular/forms';

function noCourseCode(control: AbstractControl): ValidationErrors | null {
  return typeof control.value === 'string' && control.value.startsWith('XX') ? { noCourseCode: true } : null;
}

function simulateEmailCheck(control: AbstractControl): Promise<ValidationErrors | null> {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(typeof control.value === 'string' && control.value.includes('test@') ? { emailTaken: true } : null);
    }, 800);
  });
}

@Component({
  selector: 'app-reactive-enrollment-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <form [formGroup]="enrollForm" (ngSubmit)="onSubmit()">
      <label>Name <input formControlName="studentName" /></label>

      <label>Email <input formControlName="studentEmail" /></label>
      <span *ngIf="enrollForm.get('studentEmail')?.errors?.['emailTaken']">Email taken</span>

      <label>Course ID <input formControlName="courseId" /></label>
      <span *ngIf="enrollForm.get('courseId')?.errors?.['noCourseCode']">Course code starting with XX is not allowed.</span>

      <label>Semester
        <select formControlName="preferredSemester">
          <option value="Odd">Odd</option>
          <option value="Even">Even</option>
        </select>
      </label>

      <label><input type="checkbox" formControlName="agreeToTerms" /> Agree to terms</label>

      <div formArrayName="additionalCourses">
        <div *ngFor="let ctrl of additionalCourses.controls; let i = index">
          <input [formControl]="asControl(ctrl)" />
          <button type="button" (click)="removeCourse(i)">Remove</button>
        </div>
      </div>
      <button type="button" (click)="addCourse()">Add Another Course</button>

      <button type="submit" [disabled]="enrollForm.invalid">Submit</button>
    </form>
  `
})
export class ReactiveEnrollmentFormComponent implements OnInit {
  enrollForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: this.fb.control('', {
        validators: [Validators.required, Validators.email],
        asyncValidators: [simulateEmailCheck]
      }),
      courseId: [null, [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', Validators.required],
      agreeToTerms: [false, Validators.requiredTrue],
      additionalCourses: this.fb.array([])
    });
  }

  get additionalCourses(): FormArray {
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  asControl(ctrl: AbstractControl): FormControl {
    return ctrl as FormControl;
  }

  addCourse(): void {
    this.additionalCourses.push(new FormControl('', Validators.required));
  }

  removeCourse(index: number): void {
    this.additionalCourses.removeAt(index);
  }

  onSubmit(): void {
    console.log(this.enrollForm.value, this.enrollForm.getRawValue());
  }
}
