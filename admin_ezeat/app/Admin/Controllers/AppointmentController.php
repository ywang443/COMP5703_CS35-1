<?php

namespace App\Admin\Controllers;

use App\Models\Appointment;
use Encore\Admin\Controllers\AdminController;
use Illuminate\Support\Facades\DB;
use Encore\Admin\Form;
use Encore\Admin\Grid;
use Encore\Admin\Show;

class AppointmentController extends AdminController
{
    /**
     * Title for current resource.
     *
     * @var string
     */
    protected $title = 'Appointment';

    /**
     * Make a grid builder.
     *
     * @return Grid
     */
    protected function grid()
    {
        $grid = new Grid(new Appointment());

        $grid->column('id', __('Id'));
        $grid->column('shop_id', __('Shop id'))->editable();
        // ->display(function($shop_id){
        //   $shop = DB::table('shops')
        //   ->where('id', $shop_id)
        //   ->first();
        //   return "$shop_id - $shop->name";
        // });
        $grid->column('user_id', __('User id'))->editable();
        $grid->column('phone', __('Phone'))->editable();
        $grid->column('name', __('Name'))->editable();
        $grid->column('mail', __('Mail'))->editable();
        $grid->column('memo', __('Memo'))->editable();

        //https://laravel-admin.org/docs/en/model-grid-spec-selector
        // //设置分类筛选器
        // $grid->selector(function (Grid\Tools\Selector $selector)use($tableList) {
        //     $selector->selectOne('table_id', '店铺分类', $tableList);
        // });
        $grid->table_id('餐桌')->editable('select', function($model){

        //查询此商家的店铺分类
          $tables = DB::table('tables')->where('shop_id',$model->shop_id)->select('id','name')->get();
          $tableList = [];//默认数据
          //组成分类数据
          foreach($tables as $v){
              $tableList[$v->id] = $v->name;
          }
        return  $tableList;
        });
        // $tempCategorys = DB::table('category')->select('id', 'title')
        // ->where('parent_id', '21')->get();
        // $categoryList =  [];
        // foreach ($tempCategorys as $tempCategory) {
        //     $categoryList[$tempCategory->id] = $tempCategory->title;
        //     // $categoryList[$i]['title'] = $tempCategory->title;
        // }
        // $grid->category_id('类目')->editable('select', $categoryList);
        
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
        $show = new Show(Appointment::findOrFail($id));

        $show->field('id', __('Id'));
        $show->field('shop_id', __('Shop id'));
        $show->field('user_id', __('User id'));
        $show->field('phone', __('Phone'));
        $show->field('name', __('Name'));
        $show->field('mail', __('Mail'));
        $show->field('memo', __('Memo'));
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
        $form = new Form(new Appointment());

        $form->number('shop_id', __('Shop id'));
        $form->number('user_id', __('User id'));
        $form->mobile('phone', __('Phone'));
        $form->text('name', __('Name'));
        $form->email('mail', __('Mail'));
        $form->text('memo', __('Memo'));

        return $form;
    }
}
