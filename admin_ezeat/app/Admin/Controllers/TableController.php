<?php

namespace App\Admin\Controllers;

use App\Models\Table;
use Encore\Admin\Controllers\AdminController;
use Encore\Admin\Form;
use Encore\Admin\Grid;
use Encore\Admin\Show;

class TableController extends AdminController
{
    /**
     * Title for current resource.
     *
     * @var string
     */
    protected $title = 'Table';

    /**
     * Make a grid builder.
     *
     * @return Grid
     */
    protected function grid()
    {
        $grid = new Grid(new Table());

        $grid->column('id', __('Id'));
        $grid->column('name', __('Name'))->editable();
        $grid->column('shop_id', __('Shop id'))->editable();
        // $grid->column('type', __('Type'))->editable();
        $grid->status('状态')->switch([
          'on'  => ['value' => 0, 'text' => '可用', 'color' => 'success'],
          'off' => ['value' => 1, 'text' => '不可用', 'color' => 'danger'],
        ]);

        $grid->column('type')->editable('select',[
          0 => "大",
          1 => "中",
          2 => "小",
        ]);
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
        $show = new Show(Table::findOrFail($id));

        $show->field('id', __('Id'));
        $show->field('name', __('Name'));
        $show->field('shop_id', __('Shop id'));
        $show->field('type', __('Type'));
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
        $form = new Form(new Table());

        $form->text('name', __('Name'));
        $form->number('shop_id', __('Shop id'));
        $form->number('status', __('Status'));
        $form->number('type', __('Type'));

        return $form;
    }
}
