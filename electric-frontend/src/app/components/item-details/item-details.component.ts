import { Component, OnInit } from '@angular/core';
import { Item } from '../../models/item';
import { ItemService } from '../../services/item.service';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { Http } from '@angular/http';

@Component({
  selector: 'app-item-details',
  templateUrl: './item-details.component.html',
  styleUrls: ['./item-details.component.css']
})
export class ItemDetailsComponent implements OnInit {

  public item: Item;
  public comment: Comment;
  public itemId: number;

  constructor(
    private itemService: ItemService,
    private router: Router,
    private http: Http,
    private route: ActivatedRoute
    ) { }

  postComment() {
    this.itemService.postComment(this.itemId, this.comment).subscribe(
      res => {
        this.item.comments = res.json();
      },
      error => {
        console.log(error.text());
      }
    );
  }

  ngOnInit() {
    this.comment = new Comment();
    this.item = new Item();

    this.route.params.forEach((params: Params) => {
      this.itemId = Number.parseInt(params['id']);
    });

    this.itemService.retrieveSpecificItem(this.itemId).subscribe(
      res => {
        console.log(res);
        this.item = res.json();
      },
      error => {
        console.log(error.text());
      }
    );
  }
}
