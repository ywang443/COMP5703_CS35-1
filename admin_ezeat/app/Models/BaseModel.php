<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

// 解决日期格式多了.000000Z的问题
use DateTimeInterface;
use Illuminate\Support\Carbon;

class BaseModel extends Model
{
    use HasFactory;
    /**
     * 解决日期格式多了.000000Z的问题
     */
    protected function serializeDate(DateTimeInterface $date)
    {
      return Carbon::instance($date)->toDateTimeString();
    }
}
