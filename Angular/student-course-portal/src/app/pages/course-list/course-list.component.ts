import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Store } from '@ngrx/store';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { CourseCardComponent } from '../../components/course-card/course-card.component';
import { Course } from '../../models/course.model';
import { loadCourses } from '../../store/course/course.actions';
import { selectAllCourses, selectCoursesLoading, selectCoursesError } from '../../store/course/course.selectors';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [CommonModule, FormsModule, CourseCardComponent],
  template: `
    <input [(ngModel)]="searchTerm" (ngModelChange)="onSearch()" placeholder="Search" />
    <p *ngIf="loading$ | async">Loading courses...</p>
    <p *ngIf="error$ | async as error">{{ error }}</p>
    <ng-container *ngIf="!(loading$ | async)">
      <div *ngFor="let course of courses$ | async; trackBy: trackByCourseId" (click)="goToDetail(course.id)">
        <app-course-card [course]="course" (enrollRequested)="onEnroll($event)"></app-course-card>
      </div>
      <p *ngIf="(courses$ | async)?.length === 0">No courses available.</p>
    </ng-container>
    <p *ngIf="selectedCourseId">Selected course ID: {{ selectedCourseId }}</p>
  `
})
export class CourseListComponent implements OnInit {
  courses$: Observable<Course[]> = this.store.select(selectAllCourses);
  loading$: Observable<boolean> = this.store.select(selectCoursesLoading);
  error$ = this.store.select(selectCoursesError);
  searchTerm = '';
  selectedCourseId: number | null = null;

  constructor(private store: Store, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.store.dispatch(loadCourses());
    this.searchTerm = this.route.snapshot.queryParamMap.get('search') || '';
  }

  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onSearch(): void {
    this.router.navigate(['courses'], { queryParams: { search: this.searchTerm } });
  }

  goToDetail(id: number): void {
    this.router.navigate(['courses', id]);
  }

  onEnroll(courseId: number): void {
    console.log('Enrolling in course:', courseId);
    this.selectedCourseId = courseId;
  }
}
