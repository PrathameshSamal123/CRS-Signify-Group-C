import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCourseComponent } from './component/add-course/add-course.component';
import { DropCourseComponent } from './component/drop-course/drop-course.component';
import { HomeComponent } from './component/home/home.component';
import { ViewAvailableCoursesComponent } from './component/view-available-courses/view-available-courses.component';
import { ViewFeeToPayComponent } from './component/view-fee-to-pay/view-fee-to-pay.component';
import { ViewRegisteredCoursesComponent } from './component/view-registered-courses/view-registered-courses.component';

const routes: Routes = [
  { path:'availableCourses' , component: ViewAvailableCoursesComponent },
  { path:'feeToPay' , component: ViewFeeToPayComponent },
  { path:'registeredCourses' , component: ViewRegisteredCoursesComponent },
  { path:'addCourse' , component: AddCourseComponent },
  { path:'dropCourse' , component: DropCourseComponent },
  { path:'' , component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
