import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { ViewFeeToPayServiceService } from 'src/app/service/view-fee-to-pay-service.service';

@Component({
  selector: 'app-view-fee-to-pay',
  templateUrl: './view-fee-to-pay.component.html',
  styleUrls: ['./view-fee-to-pay.component.css']
})
export class ViewFeeToPayComponent implements OnInit {
  getData: any[] = [];

  ngOnInit(): void {
    this.onSubmit();
  }

  constructor(private myService: ViewFeeToPayServiceService) {}

  onSubmit() {
    const user: User = { userId:14790 };
    const semester = 1;
    this.myService.viewFeeToPay(user, semester).subscribe((res : any[]) => {
      console.log(res);
      this.getData = res;
    });
  }

}
