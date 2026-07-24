import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { ActivatedRoute } from '@angular/router';
import { CourseListComponent } from './course-list.component';
import { selectAllCourses, selectCoursesLoading } from '../../store/course/course.selectors';

describe('CourseListComponent', () => {
  let fixture: ComponentFixture<CourseListComponent>;
  let store: MockStore;

  const mockCourses = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' as const }
  ];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseListComponent],
      providers: [
        provideMockStore({
          initialState: { course: { courses: mockCourses, loading: false, error: null }, enrollment: { enrolledCourseIds: [] } }
        }),
        { provide: ActivatedRoute, useValue: { snapshot: { queryParamMap: { get: () => null } } } }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseListComponent);
    store = TestBed.inject(MockStore);
    fixture.detectChanges();
  });

  it('should show the loading indicator when loading is true', () => {
    store.overrideSelector(selectCoursesLoading, true);
    store.refreshState();
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.textContent).toContain('Loading courses...');
  });

  it('should render courses from the store', () => {
    store.overrideSelector(selectAllCourses, mockCourses);
    store.overrideSelector(selectCoursesLoading, false);
    store.refreshState();
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.textContent).toContain('Data Structures');
  });
});
