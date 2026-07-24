# Student Course Portal — Digital Nurture 5.0 Angular Hands-On

Single Angular 20 (standalone API) project covering all 10 hands-ons.

## Setup
npm install
npm run mock-api   # json-server on :3000 (separate terminal)
npm start          # ng serve on :4200
npm test           # Jasmine/Karma

## Hands-On → Files
1. Setup/components        → app.component.ts, components/header, pages/home
2. Binding/lifecycle/@Input/@Output → pages/home, components/course-card
3. Directives/pipes        → directives/highlight.directive.ts, pipes/credit-label.pipe.ts
4. Template-driven forms   → pages/enrollment-form
5. Reactive forms          → pages/reactive-enrollment-form
6. Services/DI             → services/course.service.ts, services/enrollment.service.ts, components/notification (component-scoped provider)
7. Routing/guards/lazy load → app.routes.ts, features/enrollment/enrollment.routes.ts, guards/
8. HTTP/interceptors        → services/*.ts (HttpClient), interceptors/
9. NgRx                    → store/course, store/enrollment
10. Testing                → *.spec.ts files
