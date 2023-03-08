import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewAvailableCoursesComponent } from './component/view-available-courses/view-available-courses.component';
import { ViewFeeToPayComponent } from './component/view-fee-to-pay/view-fee-to-pay.component';
import { ViewRegisteredCoursesComponent } from './component/view-registered-courses/view-registered-courses.component';
import { AddCourseComponent } from './component/add-course/add-course.component';
import { DropCourseComponent } from './component/drop-course/drop-course.component';
import { HomeComponent } from './component/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    ViewAvailableCoursesComponent,
    ViewFeeToPayComponent,
    ViewRegisteredCoursesComponent,
    AddCourseComponent,
    DropCourseComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
