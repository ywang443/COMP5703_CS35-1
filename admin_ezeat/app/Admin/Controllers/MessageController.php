<?php

namespace App\Admin\Controllers;

use App\Models\Message;
use Encore\Admin\Controllers\AdminController;
use Encore\Admin\Form;
use Encore\Admin\Grid;
use Encore\Admin\Show;

class MessageController extends AdminController
{
    /**
     * Title for current resource.
     *
     * @var string
     */
    protected $title = 'Message';

    /**
     * Make a grid builder.
     *
     * @return Grid
     */
    protected function grid()
    {
        $grid = new Grid(new Message());

        $grid->column('id', __('Id'));
        $grid->column('shop_id', __('Shop id'))->editable();
        $grid->column('user_id', __('User id'))->editable();
        $grid->column('title', __('Title'))->editable();
        $grid->column('content', __('Content'))->editable();
        $grid->column('isRead', __('IsRead'))->editable();
        $grid->column('type', __('Type'))->editable();
        $grid->column('status', __('Status'))->editable();
        $grid->column('created_at', __('Created at'));
        $grid->column('updated_at', __('Updated at'));

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
        $show = new Show(Message::findOrFail($id));

        $show->field('id', __('Id'));
        $show->field('shop_id', __('Shop id'));
        $show->field('user_id', __('User id'));
        $show->field('title', __('Title'));
        $show->field('content', __('Content'));
        $show->field('isRead', __('IsRead'));
        $show->field('type', __('Type'));
        $show->field('status', __('Status'));
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
        $form = new Form(new Message());

        $form->number('shop_id', __('Shop id'));
        $form->number('user_id', __('User id'));
        $form->text('title', __('Title'));
        $form->text('content', __('Content'));
        $form->switch('isRead', __('IsRead'));
        $form->switch('type', __('Type'));
        $form->switch('status', __('Status'));

        return $form;
    }
}
