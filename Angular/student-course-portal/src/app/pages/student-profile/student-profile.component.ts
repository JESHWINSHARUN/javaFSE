import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Course } from '../../models/course.model';
import { selectEnrolledCourses } from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-student-profile',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h2>My Enrolled Courses</h2>
    <ul>
      <li *ngFor="let course of enrolledCourses$ | async">{{ course.name }} ({{ course.code }})</li>
    </ul>
  `
})
export class StudentProfileComponent {
  enrolledCourses$: Observable<Course[]> = this.store.select(selectEnrolledCourses);

  constructor(private store: Store) {}
}
