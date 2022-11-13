<?php

namespace App\Admin\Controllers;

use App\Models\Shop;
use Encore\Admin\Controllers\AdminController;
use Encore\Admin\Form;
use Encore\Admin\Grid;
use Encore\Admin\Show;

class ShopController extends AdminController
{
    /**
     * Title for current resource.
     *
     * @var string
     */
    protected $title = 'Shop';

    /**
     * Make a grid builder.
     *
     * @return Grid
     */
    protected function grid()
    {
        $grid = new Grid(new Shop());

        $grid->column('id', __('Id'));
        $grid->column('name', '名称')->editable();
        $grid->column('user_id', "用户id")->editable();
        $grid->img('图片')->lightbox(['width' => 50, 'height' => 50]);
        $grid->column('phone', '电话')->editable();
        $grid->column('address', "地址")->editable();
        $grid->column('rate', "评分")->editable();
        $grid->column('description', "描述")->editable();
        $grid->column('comment', "评论")->editable();
        $grid->column('per_room', "人均")->editable();
        $grid->column('average', "房均")->editable();
        $grid->column('icons', "图标")->editable();
        $grid->column('created_at', __('Created at'));
        // $grid->column('updated_at', __('Updated at'));

        return $grid;
    }

    /**
     * Make a show builder.
     *
     * @param mixed $id
     * @return Show
     */
    protected function detail($id)
    {
        $show = new Show(Shop::findOrFail($id));

        $show->field('id', __('Id'));
        $show->field('name', __('Name'));
        $show->field('user_id', "用户id");
        $show->field('img', __('Img'));
        $show->field('phone', "电话");
        $show->field('address', "地址");
        $show->field('rate', "评分");
        $show->field('description', "描述");
        $show->field('comment', "评论");
        $show->field('per_room', "人均");
        $show->field('average', "房均");
        $show->field('icons', "图标");
        $show->field('created_at', __('Created at'));
        $show->field('updated_at', __('Updated at'));

        return $show;
    }

    /**
     * Make a form builder.
     *
     * @return Form
     */
    protected function form()
    {
        $form = new Form(new Shop());

        $form->text('name', __('Name'));
        $form->number('user_id', "用户id");
        $form->image('img', __('Img'));
        $form->mobile('phone', "电话");
        $form->text('address', "地址");
        $form->decimal('rate', "评分");
        $form->text('description', "描述");
        $form->number('comment', "评论");
        $form->number('per_room', "人均");
        $form->number('average', "房均");
        $form->text('icons', "图标");

        return $form;
    }
}
