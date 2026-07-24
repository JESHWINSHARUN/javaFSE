import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-enrollment-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <form #enrollForm="ngForm" (ngSubmit)="onSubmit(enrollForm)">
      <label>Name
        <input name="studentName" required minlength="3" [(ngModel)]="model.studentName" #nameCtrl="ngModel" />
      </label>
      <span *ngIf="nameCtrl.touched && nameCtrl.errors?.['required']">Name is required</span>
      <span *ngIf="nameCtrl.touched && nameCtrl.errors?.['minlength']">Name must be at least 3 characters</span>

      <label>Email
        <input name="studentEmail" type="email" required email [(ngModel)]="model.studentEmail" #emailCtrl="ngModel" />
      </label>
      <span *ngIf="emailCtrl.touched && emailCtrl.errors?.['required']">Email is required</span>
      <span *ngIf="emailCtrl.touched && emailCtrl.errors?.['email']">Invalid email</span>

      <label>Course ID
        <input name="courseId" type="number" required [(ngModel)]="model.courseId" />
      </label>

      <label>Preferred Semester
        <select name="preferredSemester" [(ngModel)]="model.preferredSemester">
          <option value="Odd">Odd</option>
          <option value="Even">Even</option>
        </select>
      </label>

      <label>
        <input name="agreeToTerms" type="checkbox" required [(ngModel)]="model.agreeToTerms" />
        I agree to the terms
      </label>

      <button type="submit" [disabled]="enrollForm.invalid">Submit</button>
      <button type="button" (click)="enrollForm.resetForm()">Reset</button>
    </form>
    <div *ngIf="submitted">Enrollment request submitted successfully!</div>
  `
})
export class EnrollmentFormComponent {
  model = {
    studentName: '',
    studentEmail: '',
    courseId: null as number | null,
    preferredSemester: 'Odd',
    agreeToTerms: false
  };
  submitted = false;

  onSubmit(form: NgForm): void {
    console.log(form.value, form.valid);
    this.submitted = true;
  }
}
