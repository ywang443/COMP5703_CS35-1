<?php

use Illuminate\Routing\Router;

Admin::routes();

Route::group([
    'prefix'        => config('admin.route.prefix'),
    'namespace'     => config('admin.route.namespace'),
    'middleware'    => config('admin.route.middleware'),
    'as'            => config('admin.route.prefix') . '.',
], function (Router $router) {

    $router->get('/', 'HomeController@index')->name('home');
    $router->resource('users', UserController::class);
    $router->resource('shops', ShopController::class);
    $router->resource('tables', TableController::class);
    $router->resource('hots', HotController::class);

    $router->resource('messages', MessageController::class);
    $router->resource('collections', CollectionController::class);
     $router->resource('appointments', AppointmentController::class);

});
